package uk.co.mb.pairstairs

import org.springframework.dao.DataIntegrityViolationException

class CoderController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [coderInstanceList: Coder.list(params), coderInstanceTotal: Coder.count()]
    }

    def create() {
        [coderInstance: new Coder(params)]
    }

    def save() {
        def coderInstance = new Coder(params)
        if (!coderInstance.save(flush: true)) {
            render(view: "create", model: [coderInstance: coderInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'coder.label', default: 'Coder'), coderInstance.id])
        redirect(action: "list")
    }

    def show(Long id) {
        def coderInstance = Coder.get(id)
        if (!coderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coder.label', default: 'Coder'), id])
            redirect(action: "list")
            return
        }

        [coderInstance: coderInstance]
    }

    def edit(Long id) {
        def coderInstance = Coder.get(id)
        if (!coderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coder.label', default: 'Coder'), id])
            redirect(action: "list")
            return
        }

        [coderInstance: coderInstance]
    }

    def update(Long id, Long version) {
        def coderInstance = Coder.get(id)
        if (!coderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coder.label', default: 'Coder'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (coderInstance.version > version) {
                coderInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'coder.label', default: 'Coder')] as Object[],
                          "Another user has updated this Coder while you were editing")
                render(view: "edit", model: [coderInstance: coderInstance])
                return
            }
        }

        coderInstance.properties = params

        if (!coderInstance.save(flush: true)) {
            render(view: "edit", model: [coderInstance: coderInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'coder.label', default: 'Coder'), coderInstance.id])
        redirect(action: "show", id: coderInstance.id)
    }

    def delete(Long id) {
        def coderInstance = Coder.get(id)
        if (!coderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'coder.label', default: 'Coder'), id])
            redirect(action: "list")
            return
        }

        try {
            coderInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'coder.label', default: 'Coder'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'coder.label', default: 'Coder'), id])
            redirect(action: "show", id: id)
        }
    }
}
