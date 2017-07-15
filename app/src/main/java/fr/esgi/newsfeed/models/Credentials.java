package fr.esgi.newsfeed.models;

/**
 * Created by antoinepelletier on 13/07/2017.
 */

public class Credentials {

    private String email;
    private String password;

	public Credentials(String email, String password) {
		this.email = email;
		this.password = password;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public String toString() {
		return "Credentials{" +
				"email='" + email + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Credentials that = (Credentials) o;

		if (!email.equals(that.email)) return false;
		return password.equals(that.password);

	}

	@Override
	public int hashCode() {
		int result = email.hashCode();
		result = 31 * result + password.hashCode();
		return result;
	}
}
