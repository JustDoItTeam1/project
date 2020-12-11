package com.sju.program.utils.AC;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author qhw
 * @date 2020/12/8
 **/
class AcNode
{
	//孩子节点用HashMap存储，能够在O(1)的时间内查找到，效率高
	Map<Character,AcNode> children=new HashMap<>();
	AcNode failNode;
	Set<Integer> wordLengthList = new HashSet<>();
}
