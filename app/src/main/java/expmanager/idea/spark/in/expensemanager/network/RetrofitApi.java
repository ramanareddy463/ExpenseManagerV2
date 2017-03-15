package expmanager.idea.spark.in.expensemanager.network;

import expmanager.idea.spark.in.expensemanager.model.AddSaleRequest;
import expmanager.idea.spark.in.expensemanager.model.AddStaffRequest;
import expmanager.idea.spark.in.expensemanager.model.AddTangibleExpenseRequest;
import expmanager.idea.spark.in.expensemanager.model.CreateOrganisationRequest;
import expmanager.idea.spark.in.expensemanager.model.ForgotPassword;
import expmanager.idea.spark.in.expensemanager.model.LoginRequest;
import expmanager.idea.spark.in.expensemanager.model.SignUpRequest;
import expmanager.idea.spark.in.expensemanager.model.UpdateTangibleExpense;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Ramana.Reddy on 3/13/2017.
 */

public class RetrofitApi {

    private static GitHubService githubServiceApiInterface;
    public static Retrofit retrofit;

    public static GitHubService getApi() {
        if (githubServiceApiInterface == null) {
            githubServiceApiInterface = null;

            /*
             *  HttpLoggingInterceptor is for printing logs of API Request
             */

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);


            /*
             *  initialize Retrofit
             */

            retrofit = new Retrofit.Builder()
                    .baseUrl(ServerURLModel.SERVER_BASE_IP)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            githubServiceApiInterface = retrofit.create(GitHubService.class);
        }
        return githubServiceApiInterface;
    }


    public interface GitHubService {

         /*
         * LOGIN
         */
        @POST(ServerURLModel.USER_LOGIN)
        Call<ResponseBody> loginExpenseManager(@Body LoginRequest requestModel);

         /*
         * USER_SIGN_UP
         */
        @POST(ServerURLModel.USER_SIGN_UP)
        Call<ResponseBody> SignUpExpenseManager(@Body SignUpRequest requestModel);

         /*
         * FORGOT_PASSWORD
         */
        @POST(ServerURLModel.FORGOT_PASSWORD)
        Call<ResponseBody> ForgotPasswordExpenseManager(@Body ForgotPassword requestModel);

        /*
        * FORGOT_PIN
        */
        @POST(ServerURLModel.FORGOT_PIN)
        Call<ResponseBody> ForgotPin(@Body ForgotPassword requestModel);

        /*
         * CREATE_ORGANISATION
         */
        @POST(ServerURLModel.CREATE_ORGANISATION)
        Call<ResponseBody> CreateOrganisation(@Header("Authtoken") String auth, @Body CreateOrganisationRequest requestModel);

         /*
         * ADD_TANGIBLE_EXPENSE
         */
        @POST(ServerURLModel.ADD_TANGIBLE_EXPENSE)
        Call<ResponseBody> AddTangibleExpense(@Header("Authtoken") String auth, @Body AddTangibleExpenseRequest requestModel);


         /*
          * GET_TANGIBLE_EXPENSE
         */
        @GET(ServerURLModel.GET_TANGIBLE_EXPENSE)
        Call<ResponseBody> GetTangibleExpense(@Header("Authtoken") String auth);

        /*
         * UPDATE_TANGIBLE_EXPENSE
         */
        @POST(ServerURLModel.UPDATE_TANGIBLE_EXPENSE)
        Call<ResponseBody> UpdateTangibleExpense(@Header("Authtoken") String auth, @Body UpdateTangibleExpense requestModel);





        /*
      * ADD_EXPENSE
      */
        @POST(ServerURLModel.ADD_EXPENSE)
        Call<ResponseBody> AddExpense(@Header("Authtoken") String auth, @Body AddTangibleExpenseRequest requestModel);


        /*
         * GET_EXPENSE
        */
        @GET(ServerURLModel.GET_EXPENSE)
        Call<ResponseBody> GetExpense(@Header("Authtoken") String auth,@Body UpdateTangibleExpense requestModel);

        /*
         * UPDATE_EXPENSE
         */
        @POST(ServerURLModel.UPDATE_EXPENSE)
        Call<ResponseBody> UpdateExpense(@Header("Authtoken") String auth, @Body UpdateTangibleExpense requestModel);







        /*
         * ADD_SALE
         */
        @POST(ServerURLModel.ADD_SALE)
        Call<ResponseBody> AddSale(@Header("Authtoken") String auth, @Body AddSaleRequest requestModel);


        /*
         * GET_SALE
        */
        @GET(ServerURLModel.GET_SALE)
        Call<ResponseBody> GetSale(@Header("Authtoken") String auth);

        /*
         * UPDATE_SALE
         */
        @POST(ServerURLModel.UPDATE_SALE)
        Call<ResponseBody> UpdateSale(@Header("Authtoken") String auth, @Body UpdateTangibleExpense requestModel);

        /*
         * ADD_STAFF
         */
        @POST(ServerURLModel.ADD_STAFF)
        Call<ResponseBody> AddStaff(@Header("Authtoken") String auth, @Body AddStaffRequest requestModel);




//        /*
//         *FETCH_ROOMS
//         */
//        @GET("api/v1/rooms")
//        Call<ResponseBody> fetchRooms(@Header("Authorization") String auth,
//                                      @Query("profileId") String id);
//
//
//        /*
//         * UPDATE_CONTACTS_TO_SERVER
//         */
//
//        @POST(ServerURLModel.LOCAL_CONTACTS_EXISTS)
//        Call<ResponseBody> updateContactsToServer(@Header("Authorization") String auth,
//                                                  @Body LocalContactsRequestModel requestModel);
//
//
//         /*
//         * SEND_INVITE_TO_PHONE_NUMBER
//         */
//
//        @POST("api/v1/invitations")
//        Call<ResponseBody> sendInviteToPhoneNumber(@Header("Authorization") String auth,
//                                                   @Body SendInviteRequestModel requestModel);
//
//
//        /*
//         * SEND_FRIEND_REQUEST_TO_HANDLE
//         */
//
//        @POST(ServerURLModel.ADD_FRIEND)
//        Call<ResponseBody> sendFriendRequestToHandle(@Header("Authorization") String auth,
//                                                     @Body SendFriendRequestToHandleModel requestModel);
//
//         /*
//         * SEND_FRIEND_REQUEST_TO_PHONE_NUMBER
//         */
//
//        @POST(ServerURLModel.ADD_FRIEND)
//        Call<ResponseBody> sendFriendRequestToPhoneNumber(@Header("Authorization") String auth,
//                                                          @Body SendFriendRequestToPhoneNumberModel requestModel);
//
//
//
//         /*
//         * ACCEPT FRIEND REQUEST
//         */
//
//        @POST(ServerURLModel.ACCEPT_REQUEST + "{id}")
//        Call<ResponseBody> acceptFriendRequest(@Header("Authorization") String auth,
//                                               @Path("id") int id,
//                                               @Body AcceptFriendRequestModel requestModel);
//
//        /*
//         * DELETE FRIEND REQUEST
//         */
//
//        @DELETE(ServerURLModel.ACCEPT_REQUEST + "{id}")
//        Call<ResponseBody> deleteFriend(@Header("Authorization") String auth,
//                                        @Path("id") int id);
//
//
//         /*
//         * IGNORE OR BLOCK FRIEND REQUEST
//         */
//
//        @POST(ServerURLModel.ACCEPT_REQUEST + "{id}/action")
//        Call<ResponseBody> ignoreOrBlockFriend(@Header("Authorization") String auth,
//                                               @Path("id") int id,@Body IgnoreOrBlockFriendRequestModel requestModel);
//
//
//
//        /*
//         *FETCH_POSTS
//         */
//        @GET("api/v1/room/{roomId}/posts")
//        Call<ResponseBody> fetchPosts(@Header("Authorization") String auth,@Path("roomId") int roomId,
//                                      @Query("profile_id") int profileId,
//                                      @Query("after_post_id") String afterPostId,
//                                      @Query("before_post_id") String beforePostId,
//                                      @Query("type") String type,
//                                      @Query("limit") String limit);
//
//
//
//         /*
//         * LOGOUT_USER
//         */
//
//        @DELETE(ServerURLModel.LOGIN_URL)
//        Call<ResponseBody> logoutUser(@Header("Authorization") String auth);
//
//
//
//         /*
//         * GET_CONFIRMED_FRIENDS
//         */
//
//        @GET(ServerURLModel.GET_CONFIRMED_FRIEND_LIST)
//        Call<ResponseBody> getConfirmedFriendList(@Header("Authorization") String auth,
//                                                  @Query("profile_id") int profileId,
//                                                  @Query("type") String type,
//                                                  @Query("offset") String accepted,
//                                                  @Query("limit") String limit);
//
//         /*
//         * MUTE_NOTIFICATION_FOR_PRIVATE
//         */
//
//        @POST(ServerURLModel.MUTE_NOTIFICATION)
//        Call<ResponseBody> muteNotificationForPrivate(@Header("Authorization") String auth,
//                                                      @Body MuteNotificationForPrivateRequestModel notificationForPrivateRequestModel);
//
//
//       /*
//        * UN_MUTE_NOTIFICATION_FOR_PRIVATE
//        */
//
//        @DELETE(ServerURLModel.UNMUTE_NOTIFICATION+"{muteId}")
//        Call<ResponseBody> unMuteNotificationForPrivate(@Header("Authorization") String auth,@Path("muteId") int muteId);
//
//
//
//         /*
//         * REPORT_ABUSE
//         */
//
//        @POST(ServerURLModel.REPORT_ABUSE)
//        Call<ResponseBody> reportAbuse(@Header("Authorization") String auth,
//                                       @Body ReportAbuseRequestModel reportAbuseRequestModel);
//
//
//
//
//         /*
//         * BLOCK_PROFILE
//         */
//
//        @POST(ServerURLModel.BLOCK_PROFILE)
//        Call<ResponseBody> blockProfile(@Header("Authorization") String auth,
//                                        @Body BlockProfileRequestModel blockProfileRequestModel);
//
//
//         /*
//         * UN_BLOCK_PROFILE
//         */
//
//        @DELETE(ServerURLModel.UN_BLOCK_PROFILE+"{blockId}")
//        Call<ResponseBody> unBlockProfile(@Header("Authorization") String auth,
//                                          @Path("blockId") int blockId);
//
//
//
//         /*
//         * EDIT_GROUP
//         */
//
//        @PATCH(ServerURLModel.EDIT_GROUP_ROOM+"{roomId}")
//        Call<ResponseBody> editGroup(@Header("Authorization") String auth,
//                                     @Path("roomId") int roomId,
//                                     @Body EditRoomRequestModel editRoomRequestModel);
//
//
//
//         /*
//         * EDIT_PROFILE
//         */
//
//        @PATCH(ServerURLModel.EDIT_PROFILE+"{profileId}")
//        Call<ResponseBody> editProfile(@Header("Authorization") String auth,
//                                       @Path("profileId") int profileId,
//                                       @Body EditProfileRequestModel editProfileRequestModel);
//
//
//          /*
//         * GET_PROFILE_WISE_UNREAD_COUNT
//         */
//
//        @GET(ServerURLModel.UNREAD_COUNT)
//        Call<ResponseBody> getProfileWiseUnreadCoumt(@Header("Authorization") String auth);
//
//
//
//
//        /*******************************************************************************
//         * ======================= PUBLIC API REQUESTS======================
//         ******************************************************************************/
//
//
//        /*
//         *FETCH_SEARCH_PUBLIC_ROOMS
//         */
//        @GET(ServerURLModel.PUBLIC_ROOM_SEARCH)
//        Call<ResponseBody> fetchSearchPublicRooms(@Header("Authorization") String auth,
//                                                  @Query("profile_id") int id,
//                                                  @Query("q") String searckKey,
//                                                  @Query("offset") int offsetValue);
//
//        /*
//         *FETCH_SEARCH_PUBLIC_ROOMS_WITHOUT_AUTH
//         */
//        @GET(ServerURLModel.PUBLIC_ROOM_SEARCH)
//        Call<ResponseBody> fetchSearchPublicRoomsWithoutAuth(@Query("q") String searckKey,
//                                                             @Query("offset") int offsetValue);
//
//
//        /*
//         *LEAVE_PUBLIC_ROOM
//         */
//        @DELETE(ServerURLModel.LEAVE_A_PUBLIC_ROOM + "{memberId}")
//        Call<ResponseBody> leavePublicRoom(@Header("Authorization") String auth,
//                                           @Path("memberId") String memberId,
//                                           @Query("profile_id") String profileId);
//
//
//        /*
//         *FETCH_TRENDING_PUBLIC_ROOMS
//         */
//        @GET(ServerURLModel.GET_TRENDING_ROOMS)
//        Call<ResponseBody> trendingPublicRooms(@Header("Authorization") String auth,
//                                               @Query("profile_id") int profileId);
//
//
//        /*
//         *FETCH_TRENDING_PUBLIC_ROOMS_WITHOUT_AUTH
//         */
//        @GET(ServerURLModel.GET_TRENDING_ROOMS)
//        Call<ResponseBody> trendingPublicRoomsWithoutAuth();
//
//
//        /*FETCH_CATEGORY_WISE_PUBLIC_ROOMS
//        */
//        @GET(ServerURLModel.GET_CATEGORY_WISE_ROOMS)
//        Call<ResponseBody> categoryWisePublicRooms(@Header("Authorization") String auth,
//                                                   @Query("category_id") String subCatId,
//                                                   @Query("profile_id") String profileId,
//                                                   @Query("offset") String offset);
//
//
//        /*
//        *FETCH_CATEGORY_WISE_PUBLIC_ROOMS_WITHOUT_AUTH
//        */
//        @GET(ServerURLModel.GET_CATEGORY_WISE_ROOMS)
//        Call<ResponseBody> subCategoryWisePublicRoomsWithoutAuth(@Query("category_id") String subCatId,
//                                                                 @Query("offset") String offset);
//
//
//        /*
//         *FETCH_CATEGORIES
//         */
//        @GET(ServerURLModel.GET_CATEGORIES)
//        Call<ResponseBody> fetchCategories(@Header("Authorization") String auth);
//
//        /*
//         *FETCH_CATEGORIES_WITHOUT_AUTH
//         */
//        @GET(ServerURLModel.GET_CATEGORIES)
//        Call<ResponseBody> fetchCategoriesAndSubCategoriesWithoutAuth();
//
////        /*
////         *JOIN_OR_FOLLOW_PUBLIC_ROOM
////         */
////        @PATCH(ServerURLModel.FETCH_PUBLIC_ROOM + "{roomId}")
////        Call<ResponseBody> joinOrFollowPublicRoom(@Header("Authorization") String auth,
////                                                  @Path("roomId") int roomId,
////                                                  @Body JoiningRequestModel joiningRequestModel);
//
//
//        /*
//        * UN_MUTE_NOTIFICATION_FOR_PUBLIC
//        */
//        @DELETE(ServerURLModel.UNMUTE_PUBLIC_NOTIFICATION + "{muteId}")
//        Call<ResponseBody> unMuteNotificationForPublic(@Header("Authorization") String auth,
//                                                       @Path("muteId") int muteId);
//
//
////        /*
////        * MUTE_NOTIFICATION_FOR_PUBLIC
////        */
////        @POST(ServerURLModel.MUTE_PUBLIC_NOTIFICATION)
////        Call<ResponseBody> muteNotificationForPublic(@Header("Authorization") String auth,
////                                                     @Body MuteRequestModel muteRequestModel);
//
//
//        /*
//        *FETCH_CHATS_FOR_PUBLIC_ROOMS
//        */
//        @GET(ServerURLModel.FETCH_PUBLIC_ROOM + "{roomId}/chats")
//        Call<ResponseBody> fetchChatsForPublicRooms(@Header("Authorization") String auth,
//                                                    @Path("roomId") String roomId,
//                                                    @Query("profile_id") String profileId,
//                                                    @Query("before_post_id") String beforePostId,
//                                                    @Query("after_post_id") String afterPostId,
//                                                    @Query("direction") String direction);
//
//        /*
//         *FETCH_PUBLIC_ROOMS_PROFILE_WISE
//         */
//        @GET("api/v2/public_rooms")
//        Call<ResponseBody> fetchPublicRoomsProfileWise(@Header("Authorization") String auth,
//                                                       @Query("profile_id") String profileId);
//
//
//        /*
//         *FETCH_PUBLIC_ROOM_MEMBERS
//         */
//        @GET(ServerURLModel.FETCH_PUBLIC_ROOM + "{roomId}/members")
//        Call<ResponseBody> fetchPublicRoomMembers(@Header("Authorization") String auth,
//                                                  @Path("roomId") int roomId);


    }
}
