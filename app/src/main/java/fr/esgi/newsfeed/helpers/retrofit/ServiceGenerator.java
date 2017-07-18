package fr.esgi.newsfeed.helpers.retrofit;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.esgi.newsfeed.helpers.Constants;
import fr.esgi.newsfeed.models.SessionToken;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServiceGenerator {

	private static Gson gson;

	private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

	private static Retrofit retrofit = getDefault();

	public static Retrofit getDefault() {
		if (retrofit == null) {
			retrofit = new Retrofit.Builder()
					.baseUrl(Constants.getBaseURL())
					.addConverterFactory(ScalarsConverterFactory.create())
					.addConverterFactory(GsonConverterFactory.create(getGson()))
					.client(httpClient.build())
					.build();
		}
		return retrofit;
	}

	public static <S> S createService(Class<S> serviceClass) {
		return createAuthService(serviceClass, null);
	}

	/**
	 * With this, you don't have to pass the auth token to every API call you make
	 *
	 * @param serviceClass The "IRF" interface service class
	 * @param authToken    The auth token
	 * @param <S>          The service class
	 * @return
	 */
	public static <S> S createAuthService(Class<S> serviceClass, final SessionToken authToken) {
		if (authToken != null && authToken.validateToken()) {
			AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken.getToken());

			if (!httpClient.interceptors().contains(interceptor)) {
				httpClient.addInterceptor(interceptor);

				retrofit = getDefault();
			}
		}

		return retrofit.create(serviceClass);
	}

	private static Gson getGson() {
		if (gson == null) {
			GsonBuilder builder = getDefaultGsonBuilder();
			gson = builder.create();
		}
		return gson;
	}

	private static GsonBuilder getDefaultGsonBuilder() {
		GsonBuilder defaultGsonBuilder = new GsonBuilder();
		defaultGsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				return f.getAnnotation(Exclude.class) != null &&
						f.getAnnotation(Exclude.class).serialize();
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}
		});
		defaultGsonBuilder.addDeserializationExclusionStrategy(new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				return f.getAnnotation(Exclude.class) != null &&
						f.getAnnotation(Exclude.class).deserialize();
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}
		});
		defaultGsonBuilder.setDateFormat("yyyy-MM-dd");
		return defaultGsonBuilder;
	}
}