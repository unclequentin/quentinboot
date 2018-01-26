package com.quentin.example;

import com.quentin.example.domain.BasicSiteVO;
import com.quentin.example.domain.OptEwbVO;
import com.quentin.example.service.IBasicSiteService;
import com.quentin.example.service.IEwbService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QuetinbootApplicationTests {
	@Value("${myapp.bignumber}")
	private long randomLongNumber;
	@Value("${myapp.number}")
	private int randomIntNumber;
	@Value("${myapp.secret}")
	private String randomStr;
	@Autowired
	private IEwbService ewbService;
	@Autowired
	private IBasicSiteService basicSiteService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRandom() {
		log.info("随机long类型："+randomLongNumber);
		log.info("随机int类型："+randomIntNumber);
		log.info("随机字符串类型："+randomStr);
	}

	/**
	 * Oracle数据源
	 * @Author: guoqun.yang
	 * @Date:   2018/1/26 9:57
	 * @param
	 * @version 1.0
	 */
	@Test
	public void testDynamicsdb() {
		OptEwbVO ewbVO = ewbService.getEwb("301702230867");
		if (null != ewbVO) {
			System.out.println(""+ewbVO.toString());
		}
	}

	/**
	 * MySQL数据源
	 * @Author: guoqun.yang
	 * @Date:   2018/1/26 9:57
	 * @param
	 * @version 1.0
	 */
	@Test
	public void testMySQLDynamicsdb() {
		BasicSiteVO basicSiteVO = basicSiteService.selectBasicSiteBySiteCode("7312000");
		if (null != basicSiteVO) {
			System.out.println("=============="+basicSiteVO.toString());
		}
	}



}
