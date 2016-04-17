package com.zhaoliang.httpclient;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;

public class HTTPAuthentication {

    /**
     * <p>
     * Any process of user authentication requires a set of credentials that can
     * be used to establish user identity. In the simplest form user credentials
     * can be just a user name / password pair. UsernamePasswordCredentials
     * represents a set of credentials consisting of a security principal and a
     * password in clear text. This implementation is sufficient for standard
     * authentication schemes defined by the HTTP standard specification.
     * </p>
     */
    public static void testUserCredentials1() {
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
            "user", "pwd");
        System.out.println(creds.getUserPrincipal().getName());
        System.out.println(creds.getPassword());
    }

    /**
     * <p>
     * NTCredentials is a Microsoft Windows specific implementation that
     * includes in addition to the user name / password pair a set of additional
     * Windows specific attributes such as the name of the user domain. In a
     * Microsoft Windows network the same user can belong to multiple domains
     * each with a different set of authorizations.
     * </p>
     */
    public static void testUserCredentials2() {
        NTCredentials creds = new NTCredentials("user", "pwd", "workstation",
                                                "domain");
        System.out.println(creds.getUserPrincipal().getName());
        System.out.println(creds.getPassword());
    }

    /**
     * <p>
     * Credentials providers are intended to maintain a set of user credentials
     * and to be able to produce user credentials for a particular
     * authentication scope. Authentication scope consists of a host name, a
     * port number, a realm name and an authentication scheme name. When
     * registering credentials with the credentials provider one can provide a
     * wild card (any host, any port, any realm, any scheme) instead of a
     * concrete attribute value. The credentials provider is then expected to be
     * able to find the closest match for a particular scope if the direct match
     * cannot be found.
     * </p>
     * <p>
     * HttpClient can work with any physical representation of a credentials
     * provider that implements the CredentialsProvider interface. The default
     * CredentialsProvider implementation called BasicCredentialsProvider is a
     * simple implementation backed by a java.util.HashMap.
     * </p>
     */
    public static void testCredentialsProvider() {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope("somehost",
                                                   AuthScope.ANY_PORT),
                                     new UsernamePasswordCredentials("u1", "p1"));
        credsProvider.setCredentials(new AuthScope("somehost", 8080),
                                     new UsernamePasswordCredentials("u2", "p2"));
        credsProvider.setCredentials(new AuthScope("otherhost", 8080,
                                                   AuthScope.ANY_REALM, "ntlm"), new UsernamePasswordCredentials(
            "u3", "p3"));
        System.out.println(credsProvider.getCredentials(new AuthScope(
            "somehost", 80, "realm", "basic")));
        System.out.println(credsProvider.getCredentials(new AuthScope(
            "somehost", 8080, "realm", "basic")));
        System.out.println(credsProvider.getCredentials(new AuthScope(
            "otherhost", 8080, "realm", "basic")));
        System.out.println(credsProvider.getCredentials(new AuthScope(
            "otherhost", 8080, null, "ntlm")));
    }
}
