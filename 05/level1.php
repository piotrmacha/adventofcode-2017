<?php

$jumps = array_map(function ($value) {
    return (int)trim($value);
}, file('data.txt'));

$index = 0;
$count = 0;
$limit = count($jumps);
for (;;) {
    $jump = $jumps[$index];
    $jumps[$index]++;
    $index += $jump;
    $count++;

    if ($index < 0 || $index >= $limit) {
        break;
    }
}

echo $count.PHP_EOL;
