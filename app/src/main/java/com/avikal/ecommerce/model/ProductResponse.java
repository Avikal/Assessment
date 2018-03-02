package com.avikal.ecommerce.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by avikaljain on 21/2/18.
 */

public class ProductResponse implements Parcelable {

    @SerializedName("rankings")
    private List<RankingsBean> rankings;
    @SerializedName("categories")
    private List<CategoriesBean> categories;

    protected ProductResponse(Parcel in) {
    }

    public static final Creator<ProductResponse> CREATOR = new Creator<ProductResponse>() {
        @Override
        public ProductResponse createFromParcel(Parcel in) {
            return new ProductResponse(in);
        }

        @Override
        public ProductResponse[] newArray(int size) {
            return new ProductResponse[size];
        }
    };

    public List<RankingsBean> getRankings() {
        return rankings;
    }


    public void setRankings(List<RankingsBean> rankings) {
        this.rankings = rankings;
    }

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static class RankingsBean implements Parcelable {
        @SerializedName("products")
        private List<ProductsRankingBean> products;
        @SerializedName("ranking")
        private String ranking;

        protected RankingsBean(Parcel in) {
            products = in.createTypedArrayList(ProductsRankingBean.CREATOR);
            ranking = in.readString();
        }

        public static final Creator<RankingsBean> CREATOR = new Creator<RankingsBean>() {
            @Override
            public RankingsBean createFromParcel(Parcel in) {
                return new RankingsBean(in);
            }

            @Override
            public RankingsBean[] newArray(int size) {
                return new RankingsBean[size];
            }
        };

        public List<ProductsRankingBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsRankingBean> products) {
            this.products = products;
        }

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(products);
            dest.writeString(ranking);
        }
    }

    public static class ProductsRankingBean implements Parcelable {
        @SerializedName("view_count")
        private int viewCount;
        @SerializedName("id")
        private int id;

        protected ProductsRankingBean(Parcel in) {
            viewCount = in.readInt();
            id = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(viewCount);
            dest.writeInt(id);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ProductsRankingBean> CREATOR = new Creator<ProductsRankingBean>() {
            @Override
            public ProductsRankingBean createFromParcel(Parcel in) {
                return new ProductsRankingBean(in);
            }

            @Override
            public ProductsRankingBean[] newArray(int size) {
                return new ProductsRankingBean[size];
            }
        };

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class VariantsBean implements Parcelable {
        @SerializedName("price")
        private String price;
        @SerializedName("size")
        private String size;
        @SerializedName("color")
        private String color;
        @SerializedName("id")
        private int id;

        public VariantsBean() {
        }

        public VariantsBean(Parcel in) {
            price = in.readString();
            size = in.readString();
            color = in.readString();
            id = in.readInt();
        }

        public static final Creator<VariantsBean> CREATOR = new Creator<VariantsBean>() {
            @Override
            public VariantsBean createFromParcel(Parcel in) {
                return new VariantsBean(in);
            }

            @Override
            public VariantsBean[] newArray(int size) {
                return new VariantsBean[size];
            }
        };

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(price);
            dest.writeString(size);
            dest.writeString(color);
            dest.writeInt(id);
        }
    }

    public static class TaxBean {
        @SerializedName("value")
        private double value;
        @SerializedName("name")
        private String name;

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ProductsBean implements Parcelable {
        @SerializedName("tax")
        private TaxBean tax;
        @SerializedName("variants")
        private List<VariantsBean> variants = new ArrayList<>();
        ;
        @SerializedName("date_added")
        private String dateAdded;
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private int id;


        protected ProductsBean(Parcel in) {
            dateAdded = in.readString();
            name = in.readString();
            id = in.readInt();
            in.readTypedList(variants, VariantsBean.CREATOR);
        }

        public static final Creator<ProductsBean> CREATOR = new Creator<ProductsBean>() {
            @Override
            public ProductsBean createFromParcel(Parcel in) {
                return new ProductsBean(in);
            }

            @Override
            public ProductsBean[] newArray(int size) {
                return new ProductsBean[size];
            }
        };

        public TaxBean getTax() {
            return tax;
        }

        public void setTax(TaxBean tax) {
            this.tax = tax;
        }

        public List<VariantsBean> getVariants() {
            return variants;
        }

        public void setVariants(ArrayList<VariantsBean> variants) {
            this.variants = variants;
        }

        public String getDateAdded() {
            return dateAdded;
        }

        public void setDateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(dateAdded);
            dest.writeString(name);
            dest.writeInt(id);
            dest.writeTypedList(variants);
        }
    }

    public static class CategoriesBean implements Parcelable {
        @SerializedName("child_categories")
        private List<String> childCategories;
        @SerializedName("products")
        private List<ProductsBean> products;
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private int id;

        private ProductsBean productsBean;

        public ProductsBean getProductsBean() {
            return productsBean;
        }

        public void setProductsBean(ProductsBean productsBean) {
            this.productsBean = productsBean;
        }

        protected CategoriesBean(Parcel in) {
            childCategories = in.createStringArrayList();
            products = in.createTypedArrayList(ProductsBean.CREATOR);
            name = in.readString();
            id = in.readInt();
        }

        public static final Creator<CategoriesBean> CREATOR = new Creator<CategoriesBean>() {
            @Override
            public CategoriesBean createFromParcel(Parcel in) {
                return new CategoriesBean(in);
            }

            @Override
            public CategoriesBean[] newArray(int size) {
                return new CategoriesBean[size];
            }
        };

        public List<String> getChildCategories() {
            return childCategories;
        }

        public void setChildCategories(List<String> childCategories) {
            this.childCategories = childCategories;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeStringList(childCategories);
            dest.writeTypedList(products);
            dest.writeString(name);
            dest.writeInt(id);
        }
    }


}
