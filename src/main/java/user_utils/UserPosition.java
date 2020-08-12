package user_utils;

/**
 * The level is used by comparator to determine the
 * priority of the user
 */
public enum UserPosition {
    TEACHER(3),
    SENIOR_STUDENT(2),
    JUNIOR_STUDENT(1);

    UserPosition(int level) {
        this.level = level;
    }

    int level;

    public int getLevel() {
        return level;
    }
}
