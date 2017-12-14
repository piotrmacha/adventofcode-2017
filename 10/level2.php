<?php

$listLength = 256;
$list = [];
for ($i = 0; $i < $listLength; ++$i) {
    $list[] = $i;
}
$rawInput = trim(file_get_contents('data.txt'));

$lengths  = [];
foreach (str_split($rawInput) as $char) {
    $lengths[] = ord($char);
}
foreach ([17, 31, 73, 47, 23] as $value) {
    $lengths[] = $value;
}

$pointer = 0;
$skip = 0;
for ($r = 0; $r < 64; ++$r) {
    foreach ($lengths as $length) {
        $length = (int)$length;
        for ($i = 0; $i < $length / 2; ++$i) {
            $current  = ($i + $pointer) % $listLength;
            $opposite = ($length - $i + $pointer - 1) % $listLength;

            $tmp             = $list[$opposite];
            $list[$opposite] = $list[$current];
            $list[$current]  = $tmp;
        }
        $pointer = $pointer + $length + $skip;
        $skip++;
    }
}
$denseList = [];
for ($i = 0; $i < 16; ++$i) {
    $xor = $list[$i * 16];
    for ($j = 1; $j < 16; ++$j) {
         $xor ^= $list[$i * 16 + $j];
    }
    $denseList[] = $xor;
}

$hash = '';
foreach ($denseList as $item) {
    $hex = dechex($item);
    if (strlen($hex) === 1) {
        $hex = '0' . $hex;
    }
    $hash .= $hex;
}

echo $hash.PHP_EOL;

