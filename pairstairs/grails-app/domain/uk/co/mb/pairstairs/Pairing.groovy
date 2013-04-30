package uk.co.mb.pairstairs

import org.joda.time.DateMidnight
import org.joda.time.DateTime
import org.joda.time.Days

class Pairing {

    DateTime pairingDate

    static hasMany = [coders: Coder]

    static constraints = {
        pairingDate(nullable: true)
    }

    int daysAgo() {
        Days.daysBetween(new DateMidnight(pairingDate), new DateMidnight()).getDays()
    }

    String toString() {
        return "${coders.join(" and ")}"
    }
}
