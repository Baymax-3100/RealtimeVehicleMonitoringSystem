
export class RaGsConditionMaker {

    static equal(data, key, value){
        if(!data.where){
            data.where = {}
        }
        if (!data.where.equal){
            data.where.equal = {}
        }
        data.where.equal[key] = value;
        return data;
    }
}