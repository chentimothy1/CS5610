(function () {

    let tbody
    let template
    let clone

    let $createBtn, $updateBtn;
    let $usernameFld, $passwordFld,
        $firstNameFld, $lastNameFld,
        $roleFld, $emailFld;

    const userService = new AdminUserServiceClient()

    let selectedUserIndex = -1
    const selectUser = (index) => {
        selectedUserIndex = index
        $("#usernameFld").val(users[index].username)
        $("#passwordFld").val(users[index].password)
        $("#emailFld").val(users[index].email)
        $("#firstNameFld").val(users[index].first)
        $("#lastNameFld").val(users[index].last)
        $("#roleFld").val(users[index].role)
    }

    const renderUsers = (users) => {
        tbody.empty()
        const ul = $("<ul>")
        // iterates over array of users
        for (let i = 0; i < users.length; i++) {
            const user = users[i]
            const li = $("<li>" + user.username + "</li>")
            ul.append(li)

            // clones template
            clone = template.clone()
            // remove hidden
            clone.removeClass("wbdv-hidden")

            // populates everything then adds the clone to the table body
            clone.find(".wbdv-username").html(user.username)
            clone.find(".wbdv-password").html(user.password)
            clone.find(".wbdv-email").html(user.email)
            clone.find(".wbdv-first-name").html(user.first)
            clone.find(".wbdv-last-name").html(user.last)
            clone.find(".wbdv-role").html(user.role)
            clone.find(".wbdv-remove").click(() => deleteUser(i))
            clone.find(".wbdv-edit").click(() => selectUser(i))

            tbody.append(clone)
        }
        container.append(ul)
    }

    const deleteUser = (_index) => {
        const user = users[_index]
        const userId = user._id

        userService.deleteUser(userId)
            .then(response => {
                users.splice(_index, 1)
                renderUsers(users)
            })
    }

    const createUser = () => {
        const username = $usernameFld.val()
        const password = $passwordFld.val()
        const email = $emailFld.val()
        const firstName = $firstNameFld.val()
        const lastName = $lastNameFld.val()
        const role = $roleFld.val()

        $usernameFld.val("")
        $passwordFld.val("")
        $emailFld.val("")
        $firstNameFld.val("")
        $lastNameFld.val("")
        $roleFld.val("")

        const newUser = {
            username: username,
            password: password,
            email: email,
            first: firstName,
            last: lastName,
            role: role
        }
        userService
            .createUser(newUser)
            .then(actualNewUser => {
                users.push(actualNewUser)
                renderUsers(users)
            })
    }


    const updateSelectedUser = () => {
        const newUsername = $("#usernameFld").val()
        const newPassword = $("#passwordFld").val()
        const newEmail = $("#emailFld").val()
        const newFirstName = $("#firstNameFld").val()
        const newLastName = $("#lastNameFld").val()
        const newRole = $("#roleFld").val()

        const userId = users[selectedUserIndex]._id
        userService.updateUser(userId, {
            username: newUsername,
            password: newPassword,
            email: newEmail,
            first: newFirstName,
            last: newLastName,
            role: newRole
        })
            .then(response => {
                users[selectedUserIndex].username = newUsername
                users[selectedUserIndex].password = newPassword
                users[selectedUserIndex].email = newEmail
                users[selectedUserIndex].first = newFirstName
                users[selectedUserIndex].last = newLastName
                users[selectedUserIndex].role = newRole
                renderUsers(users)
            })
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }


    const main = () => {

        const container = $(".container")
        tbody = $("tbody")
        template = $("tr.wbdv-template")
        $createBtn = $(".wbdv-create").click(createUser)
        $usernameFld = $("#usernameFld")
        $passwordFld = $("#passwordFld")
        $emailFld = $("#emailFld")
        $firstNameFld = $("#firstNameFld")
        $lastNameFld = $("#lastNameFld")
        $roleFld = $("#roleFld")
        $updateBtn = $(".wbdv-update").click(updateSelectedUser)

        userService.findAllUsers()
            .then((_users) => {
                console.log(_users)
                users = _users
                renderUsers(users)
            })

    }
    $(main)


})()