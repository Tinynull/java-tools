package com.zhaoliang.jackson.core.jacksonInFiveMinutes;

/**
 * Created by zhaoliang on 2017/8/10.
 */
public class User {
    private Gender _gender;
    private Name _name;
    private boolean _isVerified;
    private byte[] _userImage;

    public Name getName() {
        return _name;
    }

    public void setName(Name n) {
        _name = n;
    }

    public boolean isVerified() {
        return _isVerified;
    }

    public void setVerified(boolean b) {
        _isVerified = b;
    }

    public Gender getGender() {
        return _gender;
    }

    public void setGender(Gender g) {
        _gender = g;
    }

    public byte[] getUserImage() {
        return _userImage;
    }

    public void setUserImage(byte[] b) {
        _userImage = b;
    }

    public enum Gender {MALE, FEMALE}

    public static class Name {
        private String _first, _last;

        public String getFirst() {
            return _first;
        }

        public void setFirst(String s) {
            _first = s;
        }

        public String getLast() {
            return _last;
        }

        public void setLast(String s) {
            _last = s;
        }
    }
}
