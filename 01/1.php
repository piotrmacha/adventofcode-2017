<?php

$session = '53616c7465645f5f8d92d53c70c1fab7946955457404a91278375da34d38553ff9654cb4aad2a9ef66f2fb60e67d51bd';

function http($url, $method = 'GET', $data = []) {
	global $session;
$postdata = http_build_query(
    $data
);
	$opts = array(
	  'http'=>array(
	    'method'=>$method,
	    'header'=>"Content-type: application/x-www-form-urlencoded\r\nAccept-language: en\r\n" .
	              "Cookie: session=$session\r\n",
        'content' => $postdata
	  )
	);
	$context = stream_context_create($opts);
	return file_get_contents($url, false, $context);
}

$input = trim(http('https://adventofcode.com/2017/day/1/input'));

$input = str_split($input);
$sum = 0;

for ($i = 0; $i < count($input); ++$i) {
	if ($input[$i] === $input[($i + (count($input) / 2)) % count($input)]) {
		$sum += $input[$i];
	}
}

$respones = http('https://adventofcode.com/2017/day/1/answer', 'POST', [
	'level' => 2,
	'answer' => $sum,
	'submit' => '[Submit]'
]);

var_dump($respones);