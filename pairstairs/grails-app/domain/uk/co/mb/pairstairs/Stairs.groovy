package uk.co.mb.pairstairs

class Stairs {

    static belongsTo = [team: Team]
    static hasMany = [pairings: Pairing]

    static constraints = {
    }
}
