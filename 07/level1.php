<?php

$lines = file('data.txt');

$nodes = [];
$children = [];

foreach ($lines as $line) {
    preg_match('/^([a-z]+) \((\d+)\) ?\-?>? ?(.*)$/', $line, $matches);
    $nodes[] = $matches[1];
    foreach (explode(', ', $matches[3]) as $child) {
        if (empty($child)) {
            continue;
        }
        $children[] = $child;
    }
}

echo array_values(array_diff($nodes, $children))[0].PHP_EOL;