package com.intelligent.marking.net.model;

import java.util.List;

public class LocationInfoModel {

    @Override
    public String toString() {
        return "LocationInfoModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city.toString() +
                '}';
    }

    /**
     * id : 636
     * name : 河北省
     * city : [{"id":637,"name":"石家庄市","area":[{"id":638,"name":"市辖区"},{"id":639,"name":"长安区"},{"id":651,"name":"桥东区"},{"id":662,"name":"桥西区"},{"id":675,"name":"新华区"},{"id":691,"name":"井陉矿区"},{"id":697,"name":"裕华区"},{"id":708,"name":"井陉县"},{"id":726,"name":"正定县"},{"id":736,"name":"栾城县"},{"id":745,"name":"行唐县"},{"id":761,"name":"灵寿县"},{"id":777,"name":"高邑县"},{"id":783,"name":"深泽县"},{"id":790,"name":"赞皇县"},{"id":802,"name":"无极县"},{"id":814,"name":"平山县"},{"id":838,"name":"元氏县"},{"id":854,"name":"赵县"},{"id":866,"name":"辛集市"},{"id":882,"name":"藁城市"},{"id":898,"name":"晋州市"},{"id":909,"name":"新乐市"},{"id":922,"name":"鹿泉市"}]},{"id":936,"name":"唐山市","area":[{"id":937,"name":"市辖区"},{"id":938,"name":"路南区"},{"id":952,"name":"路北区"},{"id":965,"name":"古冶区"},{"id":977,"name":"开平区"},{"id":989,"name":"丰南区"},{"id":1007,"name":"丰润区"},{"id":1034,"name":"滦县"},{"id":1048,"name":"滦南县"},{"id":1067,"name":"乐亭县"},{"id":1085,"name":"迁西县"},{"id":1104,"name":"玉田县"},{"id":1125,"name":"唐海县"},{"id":1140,"name":"遵化市"},{"id":1168,"name":"迁安市"}]},{"id":1188,"name":"秦皇岛市","area":[{"id":1189,"name":"市辖区"},{"id":1190,"name":"海港区"},{"id":1208,"name":"山海关区"},{"id":1218,"name":"北戴河区"},{"id":1223,"name":"青龙满族自治县"},{"id":1249,"name":"昌黎县"},{"id":1266,"name":"抚宁县"},{"id":1278,"name":"卢龙县"}]},{"id":1291,"name":"邯郸市","area":[{"id":1292,"name":"市辖区"},{"id":1293,"name":"邯山区"},{"id":1307,"name":"丛台区"},{"id":1319,"name":"复兴区"},{"id":1329,"name":"峰峰矿区"},{"id":1339,"name":"邯郸县"},{"id":1350,"name":"临漳县"},{"id":1365,"name":"成安县"},{"id":1375,"name":"大名县"},{"id":1396,"name":"涉县"},{"id":1414,"name":"磁县"},{"id":1434,"name":"肥乡县"},{"id":1444,"name":"永年县"},{"id":1465,"name":"邱县"},{"id":1473,"name":"鸡泽县"},{"id":1481,"name":"广平县"},{"id":1489,"name":"馆陶县"},{"id":1498,"name":"魏县"},{"id":1520,"name":"曲周县"},{"id":1531,"name":"武安市"}]}]
     */

    private int id;
    private String name;
    private List<CityBean> city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    public static class CityBean {
        @Override
        public String toString() {
            return "CityBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
//                    ", area=" + area.toString() +
                    '}';
        }

        /**
         * id : 637
         * name : 石家庄市
         * area : [{"id":638,"name":"市辖区"},{"id":639,"name":"长安区"},{"id":651,"name":"桥东区"},{"id":662,"name":"桥西区"},{"id":675,"name":"新华区"},{"id":691,"name":"井陉矿区"},{"id":697,"name":"裕华区"},{"id":708,"name":"井陉县"},{"id":726,"name":"正定县"},{"id":736,"name":"栾城县"},{"id":745,"name":"行唐县"},{"id":761,"name":"灵寿县"},{"id":777,"name":"高邑县"},{"id":783,"name":"深泽县"},{"id":790,"name":"赞皇县"},{"id":802,"name":"无极县"},{"id":814,"name":"平山县"},{"id":838,"name":"元氏县"},{"id":854,"name":"赵县"},{"id":866,"name":"辛集市"},{"id":882,"name":"藁城市"},{"id":898,"name":"晋州市"},{"id":909,"name":"新乐市"},{"id":922,"name":"鹿泉市"}]
         */

        private int id;
        private String name;
        private List<AreaBean> area;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<AreaBean> getArea() {
            return area;
        }

        public void setArea(List<AreaBean> area) {
            this.area = area;
        }

        public static class AreaBean {
            @Override
            public String toString() {
                return "AreaBean{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }

            /**
             * id : 638
             * name : 市辖区
             */


            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
