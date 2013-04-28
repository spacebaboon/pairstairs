package uk.co.mb.pairstairs

import static org.junit.Assert.*
import org.junit.*

class CoderTests {

    Coder ben,ash,robin,fahran

    @Before
    void setUp() {
        ben = new Coder(name: "Ben").save()
        ash = new Coder(name: "Ashish").save()
        robin = new Coder(name: "Robin").save()
        fahran = new Coder(name: "Fahran").save()

        new Pairing(coders: [ben, ash]).save()
    }

    @Test
    void testIdentifyExistingPair() {

        assert Pairing.list().find{ it.coders.containsAll([ash,ben]) }

        assert !Pairing.list().find{ it.coders.containsAll([ash,fahran]) }
    }
}
