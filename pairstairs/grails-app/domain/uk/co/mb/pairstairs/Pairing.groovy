package uk.co.mb.pairstairs

import org.joda.time.DateMidnight
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class Pairing {

    DateTime date

    static hasMany = [coders: Coder]

    static constraints = {
    }

    int daysAgo() {
        Days.daysBetween(new DateMidnight(date), new DateMidnight()).getDays()
    }

    String toString() {
        return "${coders.join(" and ")}"
    }
}
