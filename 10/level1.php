<?php

$listLength = 256;
$list = [];
for ($i = 0; $i < $listLength; ++$i) {
    $list[] = $i;
}
$lengths = explode(',', file_get_contents('data.txt'));

$pointer = 0;
$skip = 0;
foreach ($lengths as $length) {
    $length = (int)$length;
    for ($i = 0; $i < $length / 2; ++$i) {
        $current = ($i + $pointer) % $listLength;
        $opposite = ($length - $i + $pointer - 1) % $listLength;

        $tmp = $list[$opposite];
        $list[$opposite] = $list[$current];
        $list[$current] = $tmp;
    }
    $pointer = $pointer + $length + $skip;
    $skip++;
}

echo ($list[0] * $list[1]).PHP_EOL;

