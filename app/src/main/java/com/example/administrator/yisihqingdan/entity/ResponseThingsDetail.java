package com.example.administrator.yisihqingdan.entity;

import java.util.List;

/**
 * Created by Yishi on 2016/11/14.
 */

public class ResponseThingsDetail {

    /**
     * code : 0
     * message : Success
     * data : {"reviews":[{"id":17365,"targetType":2,"targetId":213,"ratingValue":5,"body":"是李施德林所有味道中，最不辣的！","hasBody":true,"isUnassigned":false,"isLiked":false,"likeCount":0,"commentCount":0,"hitCount":0,"shareCount":0,"imageCount":0,"images":[],"createdAt":"2016-10-29 18:51:24","createdAtDiffForHumans":"1周前","thing":{"id":213,"name":"绿茶精华漱口水","fullName":"Listerine/李施德林 绿茶精华漱口水","featuredImageUrl":"http://img01.eqingdan.com/955f5456-2f7c-11e5-afdb-00163e002745.jpeg?imageView2/1/w/320/h/320/q/75","price":"￥39","brand":{"name":"Listerine/李施德林","slug":"listerine-li-shi-de-lin"}},"links":{"self":"http://api.eqingdan.com/v1/reviews/17365","share":"http://www.eqingdan.com/mobile/reviews/17365"},"user":{"id":3,"username":"tinco","nickname":"Tinco","tagline":"最擅长三分钟热度","avatarUrl":"http://img01.eqingdan.com/979e5968-07a1-11e5-aaae-00163e002745.jpeg?imageView2/1/w/120/h/120/q/75"}},{"id":17383,"targetType":2,"targetId":213,"ratingValue":5,"body":"好","hasBody":true,"isUnassigned":false,"isLiked":false,"likeCount":0,"commentCount":0,"hitCount":0,"shareCount":0,"imageCount":0,"images":[],"createdAt":"2016-10-29 23:09:38","createdAtDiffForHumans":"1周前","thing":{"id":213,"name":"绿茶精华漱口水","fullName":"Listerine/李施德林 绿茶精华漱口水","featuredImageUrl":"http://img01.eqingdan.com/955f5456-2f7c-11e5-afdb-00163e002745.jpeg?imageView2/1/w/320/h/320/q/75","price":"￥39","brand":{"name":"Listerine/李施德林","slug":"listerine-li-shi-de-lin"}},"links":{"self":"http://api.eqingdan.com/v1/reviews/17383","share":"http://www.eqingdan.com/mobile/reviews/17383"},"user":{"id":42509,"username":"jin-jin-hlpsg7","nickname":"金金-hlPsG7","tagline":"","avatarUrl":"http://img01.eqingdan.com/87a698b0-9de9-11e6-a287-00163e002745.jpeg?imageView2/1/w/120/h/120/q/75"}}],"meta":{"pagination":{"total":2,"count":2,"per_page":3,"current_page":1,"total_pages":1,"links":{}}}}
     */

    private int code;
    private String message;
    /**
     * reviews : [{"id":17365,"targetType":2,"targetId":213,"ratingValue":5,"body":"是李施德林所有味道中，最不辣的！","hasBody":true,"isUnassigned":false,"isLiked":false,"likeCount":0,"commentCount":0,"hitCount":0,"shareCount":0,"imageCount":0,"images":[],"createdAt":"2016-10-29 18:51:24","createdAtDiffForHumans":"1周前","thing":{"id":213,"name":"绿茶精华漱口水","fullName":"Listerine/李施德林 绿茶精华漱口水","featuredImageUrl":"http://img01.eqingdan.com/955f5456-2f7c-11e5-afdb-00163e002745.jpeg?imageView2/1/w/320/h/320/q/75","price":"￥39","brand":{"name":"Listerine/李施德林","slug":"listerine-li-shi-de-lin"}},"links":{"self":"http://api.eqingdan.com/v1/reviews/17365","share":"http://www.eqingdan.com/mobile/reviews/17365"},"user":{"id":3,"username":"tinco","nickname":"Tinco","tagline":"最擅长三分钟热度","avatarUrl":"http://img01.eqingdan.com/979e5968-07a1-11e5-aaae-00163e002745.jpeg?imageView2/1/w/120/h/120/q/75"}},{"id":17383,"targetType":2,"targetId":213,"ratingValue":5,"body":"好","hasBody":true,"isUnassigned":false,"isLiked":false,"likeCount":0,"commentCount":0,"hitCount":0,"shareCount":0,"imageCount":0,"images":[],"createdAt":"2016-10-29 23:09:38","createdAtDiffForHumans":"1周前","thing":{"id":213,"name":"绿茶精华漱口水","fullName":"Listerine/李施德林 绿茶精华漱口水","featuredImageUrl":"http://img01.eqingdan.com/955f5456-2f7c-11e5-afdb-00163e002745.jpeg?imageView2/1/w/320/h/320/q/75","price":"￥39","brand":{"name":"Listerine/李施德林","slug":"listerine-li-shi-de-lin"}},"links":{"self":"http://api.eqingdan.com/v1/reviews/17383","share":"http://www.eqingdan.com/mobile/reviews/17383"},"user":{"id":42509,"username":"jin-jin-hlpsg7","nickname":"金金-hlPsG7","tagline":"","avatarUrl":"http://img01.eqingdan.com/87a698b0-9de9-11e6-a287-00163e002745.jpeg?imageView2/1/w/120/h/120/q/75"}}]
     * meta : {"pagination":{"total":2,"count":2,"per_page":3,"current_page":1,"total_pages":1,"links":{}}}
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pagination : {"total":2,"count":2,"per_page":3,"current_page":1,"total_pages":1,"links":{}}
         */

        private MetaBean meta;
        /**
         * id : 17365
         * targetType : 2
         * targetId : 213
         * ratingValue : 5
         * body : 是李施德林所有味道中，最不辣的！
         * hasBody : true
         * isUnassigned : false
         * isLiked : false
         * likeCount : 0
         * commentCount : 0
         * hitCount : 0
         * shareCount : 0
         * imageCount : 0
         * images : []
         * createdAt : 2016-10-29 18:51:24
         * createdAtDiffForHumans : 1周前
         * thing : {"id":213,"name":"绿茶精华漱口水","fullName":"Listerine/李施德林 绿茶精华漱口水","featuredImageUrl":"http://img01.eqingdan.com/955f5456-2f7c-11e5-afdb-00163e002745.jpeg?imageView2/1/w/320/h/320/q/75","price":"￥39","brand":{"name":"Listerine/李施德林","slug":"listerine-li-shi-de-lin"}}
         * links : {"self":"http://api.eqingdan.com/v1/reviews/17365","share":"http://www.eqingdan.com/mobile/reviews/17365"}
         * user : {"id":3,"username":"tinco","nickname":"Tinco","tagline":"最擅长三分钟热度","avatarUrl":"http://img01.eqingdan.com/979e5968-07a1-11e5-aaae-00163e002745.jpeg?imageView2/1/w/120/h/120/q/75"}
         */

        private List<ReviewsBean> reviews;

        public MetaBean getMeta() {
            return meta;
        }

        public void setMeta(MetaBean meta) {
            this.meta = meta;
        }

        public List<ReviewsBean> getReviews() {
            return reviews;
        }

        public void setReviews(List<ReviewsBean> reviews) {
            this.reviews = reviews;
        }

        public static class MetaBean {
            /**
             * total : 2
             * count : 2
             * per_page : 3
             * current_page : 1
             * total_pages : 1
             * links : {}
             */

            private PaginationBean pagination;

            public PaginationBean getPagination() {
                return pagination;
            }

            public void setPagination(PaginationBean pagination) {
                this.pagination = pagination;
            }

            public static class PaginationBean {
                private int total;
                private int count;
                private int per_page;
                private int current_page;
                private int total_pages;

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public int getPer_page() {
                    return per_page;
                }

                public void setPer_page(int per_page) {
                    this.per_page = per_page;
                }

                public int getCurrent_page() {
                    return current_page;
                }

                public void setCurrent_page(int current_page) {
                    this.current_page = current_page;
                }

                public int getTotal_pages() {
                    return total_pages;
                }

                public void setTotal_pages(int total_pages) {
                    this.total_pages = total_pages;
                }
            }
        }

        public static class ReviewsBean {
            private int id;
            private int targetType;
            private int targetId;
            private int ratingValue;
            private String body;
            private boolean hasBody;
            private boolean isUnassigned;
            private boolean isLiked;
            private int likeCount;
            private int commentCount;
            private int hitCount;
            private int shareCount;
            private int imageCount;
            private String createdAt;
            private String createdAtDiffForHumans;
            /**
             * id : 213
             * name : 绿茶精华漱口水
             * fullName : Listerine/李施德林 绿茶精华漱口水
             * featuredImageUrl : http://img01.eqingdan.com/955f5456-2f7c-11e5-afdb-00163e002745.jpeg?imageView2/1/w/320/h/320/q/75
             * price : ￥39
             * brand : {"name":"Listerine/李施德林","slug":"listerine-li-shi-de-lin"}
             */

            private ThingBean thing;
            /**
             * self : http://api.eqingdan.com/v1/reviews/17365
             * share : http://www.eqingdan.com/mobile/reviews/17365
             */

            private LinksBean links;
            /**
             * id : 3
             * username : tinco
             * nickname : Tinco
             * tagline : 最擅长三分钟热度
             * avatarUrl : http://img01.eqingdan.com/979e5968-07a1-11e5-aaae-00163e002745.jpeg?imageView2/1/w/120/h/120/q/75
             */

            private UserBean user;
            private List<?> images;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTargetType() {
                return targetType;
            }

            public void setTargetType(int targetType) {
                this.targetType = targetType;
            }

            public int getTargetId() {
                return targetId;
            }

            public void setTargetId(int targetId) {
                this.targetId = targetId;
            }

            public int getRatingValue() {
                return ratingValue;
            }

            public void setRatingValue(int ratingValue) {
                this.ratingValue = ratingValue;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public boolean isHasBody() {
                return hasBody;
            }

            public void setHasBody(boolean hasBody) {
                this.hasBody = hasBody;
            }

            public boolean isIsUnassigned() {
                return isUnassigned;
            }

            public void setIsUnassigned(boolean isUnassigned) {
                this.isUnassigned = isUnassigned;
            }

            public boolean isIsLiked() {
                return isLiked;
            }

            public void setIsLiked(boolean isLiked) {
                this.isLiked = isLiked;
            }

            public int getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(int likeCount) {
                this.likeCount = likeCount;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public int getHitCount() {
                return hitCount;
            }

            public void setHitCount(int hitCount) {
                this.hitCount = hitCount;
            }

            public int getShareCount() {
                return shareCount;
            }

            public void setShareCount(int shareCount) {
                this.shareCount = shareCount;
            }

            public int getImageCount() {
                return imageCount;
            }

            public void setImageCount(int imageCount) {
                this.imageCount = imageCount;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getCreatedAtDiffForHumans() {
                return createdAtDiffForHumans;
            }

            public void setCreatedAtDiffForHumans(String createdAtDiffForHumans) {
                this.createdAtDiffForHumans = createdAtDiffForHumans;
            }

            public ThingBean getThing() {
                return thing;
            }

            public void setThing(ThingBean thing) {
                this.thing = thing;
            }

            public LinksBean getLinks() {
                return links;
            }

            public void setLinks(LinksBean links) {
                this.links = links;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }

            public static class ThingBean {
                private int id;
                private String name;
                private String fullName;
                private String featuredImageUrl;
                private String price;
                /**
                 * name : Listerine/李施德林
                 * slug : listerine-li-shi-de-lin
                 */

                private BrandBean brand;

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

                public String getFullName() {
                    return fullName;
                }

                public void setFullName(String fullName) {
                    this.fullName = fullName;
                }

                public String getFeaturedImageUrl() {
                    return featuredImageUrl;
                }

                public void setFeaturedImageUrl(String featuredImageUrl) {
                    this.featuredImageUrl = featuredImageUrl;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public BrandBean getBrand() {
                    return brand;
                }

                public void setBrand(BrandBean brand) {
                    this.brand = brand;
                }

                public static class BrandBean {
                    private String name;
                    private String slug;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getSlug() {
                        return slug;
                    }

                    public void setSlug(String slug) {
                        this.slug = slug;
                    }
                }
            }

            public static class LinksBean {
                private String self;
                private String share;

                public String getSelf() {
                    return self;
                }

                public void setSelf(String self) {
                    this.self = self;
                }

                public String getShare() {
                    return share;
                }

                public void setShare(String share) {
                    this.share = share;
                }
            }

            public static class UserBean {
                private int id;
                private String username;
                private String nickname;
                private String tagline;
                private String avatarUrl;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getTagline() {
                    return tagline;
                }

                public void setTagline(String tagline) {
                    this.tagline = tagline;
                }

                public String getAvatarUrl() {
                    return avatarUrl;
                }

                public void setAvatarUrl(String avatarUrl) {
                    this.avatarUrl = avatarUrl;
                }
            }
        }
    }
}
