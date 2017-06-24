package hlj.zhihunews.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2017/6/21 0021.
 */

public class NewsList {

    /**
     * date : 20170621
     * stories : [{"images":["https://pic2.zhimg.com/v2-b22214748254f598d454aa9b6ba3cda1.jpg"],"type":0,"id":9486288,"ga_prefix":"062113","title":"-「我就是泰山它儿子」\r\n-「待好别动，一块地砖怎么这么多话」"},{"images":["https://pic3.zhimg.com/v2-49a60e6fefea6ac0ef17b20509ef8bd2.jpg"],"type":0,"id":9483901,"ga_prefix":"062112","title":"大误 · 西部世界在中国开一家分店"},{"title":"我们分析了 815 部电影，发现电影评分真的存在偏差","ga_prefix":"062111","images":["https://pic4.zhimg.com/v2-465d23222db985c1986f31dd0374bc2f.jpg"],"multipic":true,"type":0,"id":9486313},{"images":["https://pic3.zhimg.com/v2-1f3c45ffcb628bd4917bec65985d5836.jpg"],"type":0,"id":9485819,"ga_prefix":"062110","title":"我现在状态来了，你们谁也拦不住的"},{"images":["https://pic2.zhimg.com/v2-06b075114c022da93c1e4c8523079b1d.jpg"],"type":0,"id":9484710,"ga_prefix":"062109","title":"虽然很无奈，但一家公司越有钱，你越容易受到就业歧视"},{"images":["https://pic1.zhimg.com/v2-c56b9937720ed95c1c09f591505b8230.jpg"],"type":0,"id":9485857,"ga_prefix":"062108","title":"人类的大脑有哪些天生的设计缺陷？"},{"images":["https://pic1.zhimg.com/v2-7a93333596a62ca7014f90c0b7b017fc.jpg"],"type":0,"id":9485814,"ga_prefix":"062107","title":"游戏主机是有危机没错，但这和 PC 无关，也更和性能无关"},{"title":"因为一部电影，我爱上了一座城市","ga_prefix":"062107","images":["https://pic3.zhimg.com/v2-2f60f84a4e05226e48afb59378eb0b2e.jpg"],"multipic":true,"type":0,"id":9484913},{"title":"太难看不懂，太简单容易被黑，每天都用的验证码花样越来越多了","ga_prefix":"062107","images":["https://pic2.zhimg.com/v2-7b4b2869ba5fc6f6dda17d39bf7b0c01.jpg"],"multipic":true,"type":0,"id":9485805},{"images":["https://pic3.zhimg.com/v2-f7d74b22387baaab2ec6776e97642ffa.jpg"],"type":0,"id":9483945,"ga_prefix":"062106","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-fc35dee46134fa7af72a9c3ce62b22c8.jpg","type":0,"id":9485814,"ga_prefix":"062107","title":"游戏主机是有危机没错，但这和 PC 无关，也更和性能无关"},{"image":"https://pic4.zhimg.com/v2-820a449977e03a5c0da3e16d67eab51f.jpg","type":0,"id":9484710,"ga_prefix":"062109","title":"虽然很无奈，但一家公司越有钱，你越容易受到就业歧视"},{"image":"https://pic1.zhimg.com/v2-e9452557077cf31a8c13a7b2f8f21b58.jpg","type":0,"id":9485805,"ga_prefix":"062107","title":"太难看不懂，太简单容易被黑，每天都用的验证码花样越来越多了"},{"image":"https://pic2.zhimg.com/v2-602240aa0a207a456094d0a7241f77ed.jpg","type":0,"id":9485033,"ga_prefix":"062017","title":"为了防盗版，这些游戏厂商在搞笑的道路上越走越远"},{"image":"https://pic3.zhimg.com/v2-a027d1d3115e0cd3c07097c5de64695e.jpg","type":0,"id":9483361,"ga_prefix":"062015","title":"什么？《进击的巨人》第二季完结了？我还没看够啊"}]
     */

    private java.util.List<StoriesBean> stories;

    public static class StoriesBean implements Parcelable {
        /**
         * images : ["https://pic2.zhimg.com/v2-b22214748254f598d454aa9b6ba3cda1.jpg"]
         * type : 0
         * id : 9486288
         * ga_prefix : 062113
         * title : -「我就是泰山它儿子」
         -「待好别动，一块地砖怎么这么多话」
         * multipic : true
         */

        private int id;
        private String title;
        private java.util.List<String> images;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.title);
            dest.writeStringList(this.images);
        }

        public StoriesBean() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StoriesBean that = (StoriesBean) o;
            return id == that.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        protected StoriesBean(Parcel in) {
            this.id = in.readInt();
            this.title = in.readString();
            this.images = in.createStringArrayList();
        }

        public static final Parcelable.Creator<StoriesBean> CREATOR = new Parcelable.Creator<StoriesBean>() {
            @Override
            public StoriesBean createFromParcel(Parcel source) {
                return new StoriesBean(source);
            }

            @Override
            public StoriesBean[] newArray(int size) {
                return new StoriesBean[size];
            }
        };
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }
}
