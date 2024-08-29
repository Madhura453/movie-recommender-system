package com.recommendation.constants;

public class SqlQueryConstant {

    public final static String RECOMMENDATION_QUERY =

            "select distinct m.* from movie m join movie_genres_list mg on m.movie_id = mg.movie_id " +
                    "join genres g on g.genres_id = mg.genres_id join movie_actors ma on ma.movie_id=m.movie_id " +
                    "join actor a on ma.actor_id=a.actor_id " +
                    "left join rating r on r.movie_id=m.movie_id"+
                    " where (g.name in (:genresNames) or m.director= :directorName " +
                    "or a.name in (:actorNames) or MATCH(m.description) " +
                    "AGAINST(:description IN NATURAL LANGUAGE MODE) or (r.visited=true and r.user_id= :userId) ) " +
                    "and m.movie_id!= :movieId";
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public final static String DEFAULT_PAGE_SIZE = "2";

    public static final String USER_LIKED_QUERY = "select GROUP_CONCAT(movie_id) as groupIds from rating where user_id!=:userId and like_status='LIKED' and user_rating > 3 group by user_id having find_in_set(:movieId, groupIds);";
}
