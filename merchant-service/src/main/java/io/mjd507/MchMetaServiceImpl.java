package io.mjd507;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mjd on 2018/4/18 19:25
 */
@Service
public class MchMetaServiceImpl implements MchMetaService {

  @Autowired
  MchMetaServiceMapper mchMetaServiceMapper;

  @Override
  public List<MchMetaDto> getWaitActiveMerchant() {
    List<MchMetaDo> waitActiveMerchant = mchMetaServiceMapper.getWaitActiveMerchant();
    return CopyUtils.copyList(waitActiveMerchant, MchMetaDto.class);
  }

  @Override
  public int activeMerchant(String mchId) {
    return mchMetaServiceMapper.activeMerchant(mchId);
  }

  @Override
  public int addMerchant(MchMetaDo mchMetaDo) {
    return mchMetaServiceMapper.addMerchant(mchMetaDo);
  }

  @Override
  public int updateMerchant(MchMetaDo mchMetaDo) {
    return mchMetaServiceMapper.updateSelective(mchMetaDo);
  }

  @Override
  public int deleteMerchant(String mchUserId) {
    return mchMetaServiceMapper.deleteMerchant(mchUserId);
  }

  @Override
  public List<MchMetaDto> getActiveMerchants() {
    List<MchMetaDo> mchMetaDoList = mchMetaServiceMapper.getActiveMerchants();
    return CopyUtils.copyList(mchMetaDoList, MchMetaDto.class);
  }

  @Override
  public MchMetaDto getMchByUserId(String mchUserId) {
    MchMetaDo mchMetaDo = mchMetaServiceMapper.getMchByUserId(mchUserId);
    return CopyUtils.copyObject(mchMetaDo, MchMetaDto.class);
  }

}
