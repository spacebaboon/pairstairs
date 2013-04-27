package uk.co.mb.pairstairs



import org.junit.*
import grails.test.mixin.*

@TestFor(CoderController)
@Mock(Coder)
class CoderControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/coder/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.coderInstanceList.size() == 0
        assert model.coderInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.coderInstance != null
    }

    void testSave() {
        controller.save()

        assert model.coderInstance != null
        assert view == '/coder/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/coder/show/1'
        assert controller.flash.message != null
        assert Coder.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/coder/list'

        populateValidParams(params)
        def coder = new Coder(params)

        assert coder.save() != null

        params.id = coder.id

        def model = controller.show()

        assert model.coderInstance == coder
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/coder/list'

        populateValidParams(params)
        def coder = new Coder(params)

        assert coder.save() != null

        params.id = coder.id

        def model = controller.edit()

        assert model.coderInstance == coder
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/coder/list'

        response.reset()

        populateValidParams(params)
        def coder = new Coder(params)

        assert coder.save() != null

        // test invalid parameters in update
        params.id = coder.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/coder/edit"
        assert model.coderInstance != null

        coder.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/coder/show/$coder.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        coder.clearErrors()

        populateValidParams(params)
        params.id = coder.id
        params.version = -1
        controller.update()

        assert view == "/coder/edit"
        assert model.coderInstance != null
        assert model.coderInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/coder/list'

        response.reset()

        populateValidParams(params)
        def coder = new Coder(params)

        assert coder.save() != null
        assert Coder.count() == 1

        params.id = coder.id

        controller.delete()

        assert Coder.count() == 0
        assert Coder.get(coder.id) == null
        assert response.redirectedUrl == '/coder/list'
    }
}
