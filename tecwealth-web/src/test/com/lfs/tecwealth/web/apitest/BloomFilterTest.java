package com.lfs.web.apitest;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * BloomFilterTest
 *
 * @author fushenliao
 * @version 1.0.0
 * @project demo
 * @create 2021/5/12
 * @modify 2021/5/12
 */
public class BloomFilterTest {

    public static void main(String[] args) {

        //创建字符串布隆过滤器，使用编码UTF-8
        //创建时需要传入四个参数，但我们只要关心前三个就行
        //Funnel，这是Guava中定义的一个接口，它和PrimitiveSink配套使用，主要是把任意类型的数据转化成Java基本数据类型（primitive value，如char，byte，int……），默认用java.nio.ByteBuffer实现，最终均转化为byte数组
        //expectedInsertions 期望插入数据数，int或long
        //fpp期望误判率，比如1E-7（千万分之一）
        //Strategy 策略，默认选取64位哈希映射函数，BloomFilterStrategies.MURMUR128_MITZ_64
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 200000, 1E-7);

        //置入元素，其实也有boolean类型返回，但是尊重注解，就不返回值了
        bloomFilter.put("测试测试");

        //判断元素是否存在，true存在，false不存在。
        boolean isContain = bloomFilter.mightContain("测试测试");
        System.out.println(bloomFilter.mightContain("测试测试"));
        System.out.println(bloomFilter.mightContain("测试测试2"));
        System.out.println(bloomFilter.mightContain("测试测试24"));
    }

}
