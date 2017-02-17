package com.xenonmolecule.data_collection;

/**
 * Created by MichaelRyan on 2/17/17.
 */
public class TotalSkillsRequest extends Request {

    public TotalSkillsRequest(String sku) {
        super("https://api.vexdb.io/v1/get_skills?sku=" + sku);
    }

}
