package com.wenming.weiswift.app.common.entity;

import java.util.List;

/**
 * Created by qhn on 2017/7/26.
 */

public class CropTypeEntity {
    private List<CropTypeBean> CropType;

    public List<CropTypeBean> getCropType() {
        return CropType;
    }

    public void setCropType(List<CropTypeBean> CropType) {
        this.CropType = CropType;
    }

    public static class CropTypeBean {
        /**
         * id : 1
         * name : 粮食
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


//    List<CropType> cropTypeList;
//
//    public void setCropTypeList(List<CropType> cropTypeList) {
//        this.cropTypeList = cropTypeList;
//    }
//
//    public List<CropType> getCropTypeList() {
//        return cropTypeList;
//    }
//
//    /**
//     * id : 1
//     * name : 粮食
//     */
//    public class CropType {
//        private String id;
//        private String name;
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//    }
}
