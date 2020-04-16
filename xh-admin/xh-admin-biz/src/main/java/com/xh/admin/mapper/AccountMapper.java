package com.xh.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xh.admin.entity.Account;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * AccountMapper
 * @author rubyle
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
