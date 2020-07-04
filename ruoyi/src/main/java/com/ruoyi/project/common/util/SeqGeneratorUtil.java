package com.ruoyi.project.common.util;

import io.netty.util.internal.StringUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 序号生成工具类
 *
 * @author wangdong
 * @date 2020.04.26
 */
@Slf4j
@Component
public class SeqGeneratorUtil {

    /**
     * 定义StringRedisTemplate
     */
    private static StringRedisTemplate redisTemplate;

    /**
     * 定义setStringRedisTemplateUtil
     */
    @Setter
    private static StringRedisTemplate stringRedisTemplateUtil;

    /**
     * 构造方法注入StringRedisTemplate
     *
     * @param strRedisTemplate redis模板
     */
    @Autowired
    public SeqGeneratorUtil(StringRedisTemplate strRedisTemplate) {
        redisTemplate = strRedisTemplate;
    }

    /**
     * 在依赖注入（注入StringRedisTemplate）完成后，进行初始化
     */
    @PostConstruct
    public void initSendContractMessageUtil() {
        // 调用上面定义的@Setter，进行StringRedisTemplate的初始化
        setStringRedisTemplateUtil(redisTemplate);
    }

    /**
     * 生成自增序列并返回
     *
     * @param prefix    自增序列前缀（当前日期，如20200426）
     * @param numLength 序列长度（不足此位数自动补0）
     * @return 前缀+自增序列字符串
     */
    @SuppressWarnings("unused")
    public static String seqGenerator(String prefix, int numLength) {
        // 定义获取到key对应value的中间值
        String upperCode = "";
        // 查找 prefix 的key值的数据长度
        Long size = redisTemplate.opsForList().size(prefix);
        // 有数据
        if (size != null && size > 0) {
            // 获取该key下面的所有值(-1 所有的值；1 下一个值)
            List allKeys = redisTemplate.opsForList().range(prefix, 0, -1);
            // 有数据
            if (allKeys != null && allKeys.size() > 0) {
                // 返回最后一个值
                upperCode = allKeys.get(allKeys.size() - 1).toString();
            }
        }
        // 定义返回字符串
        String returnCode;
        // 后缀数字
        int suffix;
        // 有数据
        if (!StringUtil.isNullOrEmpty(upperCode)) {
            // 截取前缀开始的后面的数字
            String sequence = upperCode.substring(prefix.length());
            suffix = Integer.parseInt(sequence);
            // 最后的序号加一
            suffix++;
        } else {
            // 没有数据
            suffix = 1;
        }
        // 后缀不够numLength长，前面补充0
        returnCode = prefix + String.format("%0" + numLength + "d", suffix);
        // 存入Redis
        redisTemplate.opsForList().rightPush(prefix, returnCode);
        // 设置24小时过期（第二天重新生成）
        redisTemplate.expire(prefix, 24 * 60 * 60, TimeUnit.SECONDS);
        log.info("调用方法SeqGeneratorUtil.SeqGenerator，入参{}，{}，反参{}", prefix, numLength, returnCode);
        return returnCode;
    }

}
