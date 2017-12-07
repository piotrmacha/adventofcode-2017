<?php

$state = [14, 0, 15, 12, 11, 11, 3, 5, 1, 6, 8, 4, 9, 1, 8, 4];

function hashState($state) {
    return array_reduce($state, function ($agg, $element) {
        return md5($agg.$element);
    });
}

$previousStateHashes = [];
$steps = 0;

for (;;) {
    $stateHash = hashState($state);
    foreach ($previousStateHashes as $previousStateHash) {
        if ($previousStateHash[0] === $stateHash) {
            echo ($steps - $previousStateHash[1]).PHP_EOL;
            break;
        }
    }
    $previousStateHashes[] = [$stateHash, $steps];

    $max = 0;
    $maxIndex = 0;
    for ($i = 0; $i < count($state); ++$i) {
        if ($state[$i] > $max) {
            $max = $state[$i];
            $maxIndex = $i;
        }
    }

    $state[$maxIndex] -= $max;
    for ($j = $maxIndex + 1; ; $j++) {
        $state[$j % count($state)] += 1;
        $max--;

        if ($max === 0) {
            break;
        }
    }

    $max = 0;
    $maxIndex = 0;

    $steps++;
}