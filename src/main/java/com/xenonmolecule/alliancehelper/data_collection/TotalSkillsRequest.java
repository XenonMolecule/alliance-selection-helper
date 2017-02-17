package com.xenonmolecule.alliancehelper.data_collection;

public class TotalSkillsRequest extends Request {

    public TotalSkillsRequest(String sku) {
        super("https://api.vexdb.io/v1/get_skills?sku=" + sku);
    }

}
