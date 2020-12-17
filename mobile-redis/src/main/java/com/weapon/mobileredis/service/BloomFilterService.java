package com.weapon.mobileredis.service;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.weapon.mobileredis.domain.SysUser;
import com.weapon.mobileredis.domain.SysUserExample;
import com.weapon.mobileredis.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;


@Service
public class BloomFilterService {

    @Resource
    private SysUserMapper sysUserMapper;

    private BloomFilter<Integer> bf;

    /**
     * 程序启动时候加载此方法
     */
    @PostConstruct
    public void initBloomFilter() {
        SysUserExample sysUserExample = new SysUserExample();
        //下面那个是查出全表数据
        List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
        if (CollectionUtils.isEmpty(sysUserList)) {
            return;
        }
        //创建布隆过滤器(默认3%误差)
        bf = BloomFilter.create(Funnels.integerFunnel(), sysUserList.size());
        for (SysUser sysUser : sysUserList) {
            bf.put(sysUser.getId());
        }
    }

    /**
     * 判断id可能存在于布隆过滤器里面
     *
     * @param id
     * @return
     */
    public boolean userIdExists(int id) {
        return bf.mightContain(id);
    }
}
