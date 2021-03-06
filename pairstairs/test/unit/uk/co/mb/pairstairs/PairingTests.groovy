package uk.co.mb.pairstairs



import grails.test.mixin.*
import org.joda.time.DateMidnight
import org.joda.time.DateTime

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Pairing)
class PairingTests {
    void testDaysAgo() {
        Coder ben = new Coder(name: 'Ben')
        Coder john = new Coder(name: 'John')
        Pairing pairing = new Pairing(coders:[ben, john], pairingDate: new DateTime(new DateMidnight()).minusHours(6))

        assert 1 == pairing.daysAgo()
    }
}
