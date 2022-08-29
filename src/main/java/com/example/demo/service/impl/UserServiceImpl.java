package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.exception.EarthquakeException;
import com.example.demo.domain.DTO.UserDTO;
import com.example.demo.domain.VO.UserVO;
import com.example.demo.domain.param.UserParam;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.DozerUtil;
import com.example.demo.util.RequestMap;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlj
 * @since 2022-01-14
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {


@Autowired
private UserMapper userMapper;


private static final String SERVICE_NAME = "";


@Override
@Transactional(rollbackFor = EarthquakeException.class, propagation = Propagation.REQUIRED)
public UserDTO save(UserDTO record) throws EarthquakeException {
    UserEntity entity = DozerUtil.trans(record, UserEntity.class);
       //给对象赋值
        int insert = userMapper.insert(entity);
        if (insert < 1) {
        log.error(String.format(SERVICE_NAME+" %s 新增失败", entity.toString()));
        throw new EarthquakeException(-1, String.format("事件 %s 新增失败", entity.toString()));
        }
        return selectByPrimaryKey(entity.getId());
        }

@Override
public UserDTO selectByPrimaryKey(String id) throws EarthquakeException {
    UserDTO dto;
        LambdaQueryWrapper< UserEntity> dictEntityQueryWrapper = new QueryWrapper< UserEntity>().lambda()
        .eq( UserEntity::getId,id);
    UserEntity entity = userMapper.selectOne(dictEntityQueryWrapper);
        if (entity == null) {
        log.error(String.format(SERVICE_NAME+"：%s 不存在", id));
        throw new EarthquakeException(-1, String.format(SERVICE_NAME+"id：%s 不存在", id));
        }
        dto = DozerUtil.trans(entity, UserDTO.class);
        log.info(String.format(SERVICE_NAME+"id：%s", entity.toString()));
        return dto;
        }

    @Override
    public UserDTO selectByName(String name) throws EarthquakeException {
        UserDTO dto;
        LambdaQueryWrapper< UserEntity> dictEntityQueryWrapper = new QueryWrapper< UserEntity>().lambda()
                .eq( UserEntity::getName,name);

        UserEntity entity = userMapper.selectOne(dictEntityQueryWrapper);
        if (entity == null) {
            log.error(String.format(SERVICE_NAME+"：%s 不存在", name));
            throw new EarthquakeException(-1, String.format(SERVICE_NAME+"id：%s 不存在", name));
        }
        dto = DozerUtil.trans(entity, UserDTO.class);
        log.info(String.format(SERVICE_NAME+"id：%s", entity.toString()));
        return dto;
    }

    @Override
@Transactional(rollbackFor = EarthquakeException.class, propagation = Propagation.REQUIRED)
public UserDTO updateByPrimaryKey(UserDTO record) throws EarthquakeException {
        // 存在性检查
        checkExist(record.getId());
    UserEntity entity = DozerUtil.trans(record, UserEntity.class);

    //赋值

        LambdaUpdateWrapper<UserEntity>userEntityWrapper = new UpdateWrapper<UserEntity>()
        .lambda()
        .eq(UserEntity::getId, entity.getId());

        int update = userMapper.update(entity, userEntityWrapper);
        if (update < 1) {
        log.error(String.format(SERVICE_NAME+" %s 修改失败", entity.toString()));
        throw new EarthquakeException(-1, String.format(SERVICE_NAME+" %s 修改失败", entity.toString()));
        }
        return selectByPrimaryKey(entity.getId());
        }

@Override
public Boolean deleteByPrimaryKey(String id) throws EarthquakeException {
        // 存在性检查
        checkExist(id);

    UserEntity  userEntity = new UserEntity();

        userEntity.setId(id);
        int deleteEffectCnt = userMapper.updateById(userEntity);

        if (deleteEffectCnt < 1) {
        log.error(String.format(SERVICE_NAME+" %s 删除失败", id));
        throw new EarthquakeException(-1, String.format(SERVICE_NAME+" %s 删除失败", id));
        }
        return true;
        }


@Override
public RequestMap selectByCondition(UserParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<UserEntity> list = getAll(param);
        PageInfo pageInfo = new PageInfo(list);
        List<UserVO> voList = DozerUtil.trans(list, UserVO.class);

        return new RequestMap(pageInfo, voList);
        }

@Override
public List<UserEntity> getAll(UserParam param) {

    //入参

        
        return null;
        }


@Override
public List<UserEntity> data(UserParam param) {
        List<UserEntity> list = new ArrayList<>();
        List<UserEntity>  userEntities = getAll(param);
        for (UserEntity userEntity : userEntities) {
        list.add(userEntity);
        }
        return list;
        }


/**
* 检查参数是否存在
* @param id
*/
private void checkExist(String id) throws EarthquakeException {

        LambdaQueryWrapper< UserEntity> dictEntityQueryWrapper = new QueryWrapper< UserEntity>().lambda()
        .eq( UserEntity::getId,id);
    UserEntity entity = userMapper.selectOne(dictEntityQueryWrapper);
        if (null == entity) {
        log.error(String.format("事件id: %s 不存在，删除失败", id));
        throw new EarthquakeException(-1,String.format("事件id: %s 不存在，删除失败", id));
        }
        }
}