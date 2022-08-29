package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Exception.EarthquakeException;
import com.example.demo.domain.DTO.DictionaryDTO;
import com.example.demo.domain.VO.DictionaryVO;
import com.example.demo.domain.param.DictionaryParam;
import com.example.demo.entity.DictionaryEntity;
import com.example.demo.mapper.DictionaryMapper;
import com.example.demo.service.DictionaryService;
import com.example.demo.utils.DozerUtil;
import com.example.demo.utils.RequestMap;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zlj
 * @since 2022-01-12
 */
@Service
@Slf4j
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, DictionaryEntity> implements DictionaryService {


@Autowired
private DictionaryMapper dictionaryMapper;



private static final String SERVICE_NAME = "";


@Override
@Transactional(rollbackFor = EarthquakeException.class, propagation = Propagation.REQUIRED)
public DictionaryDTO save(DictionaryDTO record) throws EarthquakeException {
    DictionaryEntity entity = DozerUtil.trans(record, DictionaryEntity.class);
       //给对象赋值

        int insert = dictionaryMapper.insert(entity);
        if (insert < 1) {
        log.error(String.format(SERVICE_NAME+" %s 新增失败", entity.toString()));
        throw new EarthquakeException(-1, String.format("事件 %s 新增失败", entity.toString()));
        }
        return selectByPrimaryKey(entity.getId());
        }

@Override
public DictionaryDTO selectByPrimaryKey(Long id) throws EarthquakeException {
    DictionaryDTO dto;
        LambdaQueryWrapper< DictionaryEntity> dictEntityQueryWrapper = new QueryWrapper< DictionaryEntity>().lambda()
        .eq( DictionaryEntity::getId,id)
        .isNull( DictionaryEntity::getDeleted);
    DictionaryEntity entity = dictionaryMapper.selectOne(dictEntityQueryWrapper);
        if (entity == null) {
        log.error(String.format(SERVICE_NAME+"：%s 不存在", id));
        throw new EarthquakeException(-1, String.format(SERVICE_NAME+"id：%s 不存在", id));
        }
        dto = DozerUtil.trans(entity, DictionaryDTO.class);
        log.info(String.format(SERVICE_NAME+"id：%s", entity.toString()));
        return dto;
        }

@Override
@Transactional(rollbackFor = EarthquakeException.class, propagation = Propagation.REQUIRED)
public DictionaryDTO updateByPrimaryKey(DictionaryDTO record) throws EarthquakeException {
        // 存在性检查
        checkExist(record.getId());
    DictionaryEntity entity = DozerUtil.trans(record, DictionaryEntity.class);

    //赋值

        LambdaUpdateWrapper<DictionaryEntity>dictionaryEntityWrapper = new UpdateWrapper<DictionaryEntity>()
        .lambda()
        .eq(DictionaryEntity::getId, entity.getId());

        int update = dictionaryMapper.update(entity, dictionaryEntityWrapper);
        if (update < 1) {
        log.error(String.format(SERVICE_NAME+" %s 修改失败", entity.toString()));
        throw new EarthquakeException(-1, String.format(SERVICE_NAME+" %s 修改失败", entity.toString()));
        }
        return selectByPrimaryKey(entity.getId());
        }

@Override
public Boolean deleteByPrimaryKey(Long id) throws EarthquakeException {
        // 存在性检查
        checkExist(id);

    DictionaryEntity  dictionaryEntity = new DictionaryEntity();

        dictionaryEntity.setId(id);
        dictionaryEntity.setDeleted(new Date());
        int deleteEffectCnt = dictionaryMapper.updateById(dictionaryEntity);

        if (deleteEffectCnt < 1) {
        log.error(String.format(SERVICE_NAME+" %s 删除失败", id));
        throw new EarthquakeException(-1, String.format(SERVICE_NAME+" %s 删除失败", id));
        }
        return true;
        }


@Override
public RequestMap selectByCondition(DictionaryParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<DictionaryEntity> list = getAll(param);
        PageInfo pageInfo = new PageInfo(list);
        List<DictionaryVO> voList = DozerUtil.trans(list, DictionaryVO.class);

        return new RequestMap(pageInfo, voList);
        }

@Override
public List<DictionaryEntity> getAll(DictionaryParam param) {

    //入参
    LambdaQueryWrapper<DictionaryEntity> dictEntityQueryWrapper = new QueryWrapper<DictionaryEntity>().lambda()
            .like(StringUtils.isNotBlank(param.getDictionaryName()), DictionaryEntity::getDictionaryName, param.getDictionaryName())
            .eq(StringUtils.isNotBlank(param.getDictionaryType()), DictionaryEntity::getDictionaryType, param.getDictionaryType())
            .isNull(DictionaryEntity::getDeleted)
            .orderByDesc(DictionaryEntity::getUpdated);
    return dictionaryMapper.selectList(dictEntityQueryWrapper);
        }


@Override
public List<DictionaryEntity> data(DictionaryParam param) {

    ArrayList<DictionaryEntity> list = new ArrayList<>();
    List<DictionaryEntity> all = getAll(param);
    for (DictionaryEntity dictionaryEntity : all) {
        list.add(dictionaryEntity);
    }
    return list;
        }




    /**
* 检查参数是否存在
* @param id
*/
private void checkExist(Long id) throws EarthquakeException {

        LambdaQueryWrapper< DictionaryEntity> dictEntityQueryWrapper = new QueryWrapper< DictionaryEntity>().lambda()
        .eq( DictionaryEntity::getId,id)
        .isNull( DictionaryEntity::getDeleted);
    DictionaryEntity entity = dictionaryMapper.selectOne(dictEntityQueryWrapper);
        if (null == entity) {
        log.error(String.format("事件id: %s 不存在，删除失败", id));
        throw new EarthquakeException(-1,String.format("事件id: %s 不存在，删除失败", id));
        }
        }
}