<?php

$data = file_get_contents('data.txt');

$moves = explode(',', $data);

$x = 0;
$y = 0;
$z = 0;
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
}

$distance = (abs($y) + abs($x) + abs($z)) / 2;
echo $distance.PHP_EOL;
