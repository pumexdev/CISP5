package com.pumex.ConnectedInsight.Tier.DAO;

import org.springframework.stereotype.Repository;
import com.pumex.ConnectedInsight.Tier.beans.TierProcessBean;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
@Repository
public interface TierProcessDaoInterface {
    
    public Map getProcessYear();

    public Map getTierList();
    
    public Map getYear();   

    public void addTierTarget(TierProcessBean tierProcessBean);

    public void editTierTarget(TierProcessBean tierProcessBean, Integer uid);

    public List getTierTarget(String date);

    public TierProcessBean getTierProcessList(TierProcessBean tierProcessBean, Integer uid);

    public void addTierProcess(TierProcessBean tierProcessBean);

    public List getTierProcessTarget(TierProcessBean tierProcessBean);

    public TierProcessBean editTierProcessTarget(TierProcessBean tierProcessBean, Integer uid);

    public void editTierProcess(TierProcessBean tierProcessBean, Integer uid);
}
