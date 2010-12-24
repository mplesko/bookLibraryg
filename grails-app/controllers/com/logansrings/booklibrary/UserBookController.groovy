package com.logansrings.booklibrary

import com.logansrings.booklibrary.UserController

class UserBookController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userBookInstanceList: UserBook.list(params), userBookInstanceTotal: UserBook.count()]
    }

    def create = {
        def userBookInstance = new UserBook()
        userBookInstance.properties = params
        return [userBookInstance: userBookInstance]
    }

    def save = {
        def userBookInstance = new UserBook(params)
        if (userBookInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'userBook.label', default: 'UserBook'), userBookInstance.id])}"
//            redirect(action: "show", id: userBookInstance.id)
			redirect(controller: 'user', action: 'mylibrary' )
        }
        else {
            render(view: "create", model: [userBookInstance: userBookInstance])
        }
    }

    def show = {
        def userBookInstance = UserBook.get(params.id)
        if (!userBookInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userBook.label', default: 'UserBook'), params.id])}"
            redirect(action: "list")
        }
        else {
            [userBookInstance: userBookInstance]
        }
    }

    def edit = {
        def userBookInstance = UserBook.get(params.id)
        if (!userBookInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userBook.label', default: 'UserBook'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [userBookInstance: userBookInstance]
        }
    }

    def update = {
        def userBookInstance = UserBook.get(params.id)
        if (userBookInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userBookInstance.version > version) {
                    
                    userBookInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'userBook.label', default: 'UserBook')] as Object[], "Another user has updated this UserBook while you were editing")
                    render(view: "edit", model: [userBookInstance: userBookInstance])
                    return
                }
            }
            userBookInstance.properties = params
            if (!userBookInstance.hasErrors() && userBookInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'userBook.label', default: 'UserBook'), userBookInstance.id])}"
                redirect(action: "show", id: userBookInstance.id)
            }
            else {
                render(view: "edit", model: [userBookInstance: userBookInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userBook.label', default: 'UserBook'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def userBookInstance = UserBook.get(params.id)
        if (userBookInstance) {
            try {
                userBookInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'userBook.label', default: 'UserBook'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'userBook.label', default: 'UserBook'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'userBook.label', default: 'UserBook'), params.id])}"
            redirect(action: "list")
        }
    }
}
