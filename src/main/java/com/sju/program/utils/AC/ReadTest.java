package com.sju.program.utils.AC;

/**
 * @author qhw
 * @date 2020/12/11
 **/
import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.converters.DefaultConverterLoader;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSON;

/**
 * 读的常见写法
 *
 * @author Jiaju Zhuang
 */
@Ignore
public class ReadTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReadTest.class);

	/**
	 * 最简单的读
	 * <p>
	 * 1. 创建excel对应的实体对象 参照{@link DemoData}
	 * <p>
	 * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
	 * <p>
	 * 3. 直接读即可
	 */
	@Test
	public void simpleRead() {
		// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
		// 写法1：
		String fileName = "data.xlsx";
		// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
		EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();


	}

}
