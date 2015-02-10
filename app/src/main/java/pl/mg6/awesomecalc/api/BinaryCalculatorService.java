package pl.mg6.awesomecalc.api;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface BinaryCalculatorService {

    @GET("/add")
    Observable<String> add(
            @Query("left_value") String leftValue,
            @Query("right_value") String rightValue);
}
