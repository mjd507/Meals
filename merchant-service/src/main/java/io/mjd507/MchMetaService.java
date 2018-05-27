package io.mjd507;

import java.util.List;

/**
 * Created by mjd on 2018/4/18 19:22
 */
public interface MchMetaService {

  List<MchMetaDto> getWaitActiveMerchant();

  int activeMerchant(String mchUserId);

  int addMerchant(MchMetaDo mchMetaDo);

  int updateMerchant(MchMetaDo mchMetaDo);

  int deleteMerchant(String mchUserId);

  List<MchMetaDto> getActiveMerchants();

  MchMetaDto getMchByUserId(String mchUserId);

}
