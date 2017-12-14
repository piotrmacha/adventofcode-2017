<?php

$data = file_get_contents('data.txt');

$moves = explode(',', $data);

function dist($x, $y, $z) {
    return (abs($y) + abs($x) + abs($z)) / 2;
}

$x = 0;
$y = 0;
$z = 0;
$max = 0;
foreach ($moves as $move) {
    switch ($move) {
        case 'n':
            $y++;
            $z--;
            break;
        case 'ne':
            $x++;
            $z--;
            break;
        case 'se':
            $x++;
            $y--;
            break;
        case 's':
            $y--;
            $z++;
            break;
        case 'sw':
            $x--;
            $z++;
            break;
        case 'nw':
            $x--;
            $y++;
            break;
    }
    $dist = dist($x, $y, $z);
    if ($dist > $max) {
        $max = $dist;
    }
}

echo $max.PHP_EOL;
