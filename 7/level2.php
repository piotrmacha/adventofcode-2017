<?php

$lines = file('data.txt');

class ProgramNode
{
    public $name;
    public $weight;
    /** @var \ProgramNode[] */
    public $children           = [];
    public $weightWithChildren = NULL;
    public $childrenWeights    = [];
}


// Build tree and find a root - O(n^2)
$cache                           = [];
$programsThatAreSupposedToBeRoot = [];
foreach ($lines as $line) {
    preg_match('/^([a-z]+) \((\d+)\) ?\-?>? ?(.*)$/', $line, $matches);
    $program         = new ProgramNode();
    $program->name   = $matches[1];
    $program->weight = (int)$matches[2];
    foreach (explode(', ', $matches[3]) as $child) {
        if (empty($child)) {
            continue;
        }
        $program->children[] = $child;
    }
    $programsThatAreSupposedToBeRoot[$program->name] = $program->name;
    $cache[$program->name]                           = $program;
}
foreach ($cache as $program) {
    $program->children = array_map(function ($childName) use ($cache, &$programsThatAreSupposedToBeRoot) {
        unset($programsThatAreSupposedToBeRoot[$childName]);
        return $cache[$childName];
    }, $program->children);
}
$rootName = array_reduce($programsThatAreSupposedToBeRoot, function ($agg, $name) {
    return $name;
});
$root     = $cache[$rootName];


function countWeightWithChildren(ProgramNode $node)
{
    if (count($node->children) === 0) {
        $node->weightWithChildren = $node->weight;
        return $node->weightWithChildren;
    }

    $weights = [];
    foreach ($node->children as $child) {
        $child->weightWithChildren           = countWeightWithChildren($child);
        $node->childrenWeights[$child->name] = $child->weightWithChildren;
        $node->weightWithChildren            += $child->weightWithChildren;

        if (!array_key_exists($child->weightWithChildren, $weights)) {
            $weights[$child->weightWithChildren] = [0, $child->name, $child->weightWithChildren];
        }
        $weights[$child->weightWithChildren][0]++;
    }
    if (count($weights) === 2) {
        $flat = array_values($weights);
        $diff = $flat[0][0] === 1 ? $flat[0][2] - $flat[1][2] : $flat[1][2] - $flat[0][2];
        $name = $flat[0][0] === 1 ? $flat[0][1] : $flat[1][1];
        foreach ($node->children as $child) {
            if ($child->name === $name) {
                echo $child->weight - $diff;
                die();
            }
        }
    }

    $node->weightWithChildren += $node->weight;
    return $node->weightWithChildren;
}
countWeightWithChildren($root);

echo 'Not Found' . PHP_EOL;