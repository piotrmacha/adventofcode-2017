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
    if (in_array($stateHash, $previousStateHashes)) {
        break;
    }
    $previousStateHashes[] = $stateHash;

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

echo $steps.PHP_EOL;