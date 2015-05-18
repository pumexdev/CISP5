package com.pumex.ConnectedInsight.Tier.DAO;

import com.pumex.ConnectedInsight.Tier.beans.TierBean;
import com.pumex.ConnectedInsight.Tier.beans.TierProcessBean;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface TierDaoInterface
{
    public List getTierList(TierBean tierBean);

    public TierBean getTierList(Integer uid, TierBean tierBean);

    public void addTier(TierBean tierBean);

    public void editTier(TierBean tierBean);    
    
    public List getSubProcessTarget(TierProcessBean tierProcessBean);
    
    public void addSubProcessTarget(TierProcessBean tierProcessBean);

    public void updateSubProcessTarget(TierProcessBean tierProcessBean, Integer uid);

    public TierProcessBean getSubProcessData(TierProcessBean tierProcessBean, Integer uid);
}
