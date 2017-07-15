package fr.esgi.newsfeed.models;

import java.io.Serializable;

import fr.esgi.newsfeed.helpers.retrofit.Exclude;

/**
 * Created by Ozone on 14/07/2017.
 */

public class SessionToken implements Serializable {

	@Exclude
	public static final String TOKEN_PREFIX = "Bearer ";

	private String token;

	public SessionToken(String token) {
		setToken(token);
	}

	public static boolean validateToken(String token) {
		return token.startsWith(TOKEN_PREFIX);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		if (!validateToken(token)) {
			token = TOKEN_PREFIX.concat(token);
		}
		this.token = token;
	}

	public boolean validateToken() {
		return this.token.startsWith(TOKEN_PREFIX);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SessionToken that = (SessionToken) o;

		return token.equals(that.token);

	}

	@Override
	public int hashCode() {
		return token.hashCode();
	}

	@Override
	public String toString() {
		return this.token;
	}
}
