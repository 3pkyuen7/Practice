// implementation, *1200
fun main() {
    // try to implement function using binary tree here
    val tree = BinaryTree()
    var traffic: Int = 0
    loop@ while (true) {
        val input = readLine()
        if (input.isNullOrBlank()) {
            println(traffic)
            return
        }
        when (input[0]) {
            '+' -> {
                tree.addNode(input.substring(1, input.length))
            }
            '-' -> {
                tree.size --
                tree.removeNode(input.substring(1, input.length))
            }
            else -> {
                val i = input.indexOfFirst { it == ':' }+1
                traffic += input.substring(i,input.length).length*tree.size
            }
        }
        //tree.printAll()
    }


}

class BinaryTree() {
    var size = 0
    var root: Node? = null
    fun addNode(data: String) {
        if (root == null) {
            root = Node(data)
            size++
            return
        }
        var pointer = root
        while (pointer != null) {
            if (pointer.data < data) {
                if (pointer.left == null) {
                    pointer.left = Node(data)
                    size++
                    break
                } else {
                    pointer = pointer.left
                }
            } else if (pointer.data > data) {
                if (pointer.right == null) {
                    pointer.right = Node(data)
                    size++
                    break
                } else {
                    pointer = pointer.right
                }
            } else {
                break
            }
        }
    }

    fun removeNode(data: String) {
        if (root?.data == data) {
            root = null
            return
        }
        var pointer = root
        var preNode = root
        while (pointer != null) {
            if (pointer.data < data) {
                preNode = pointer
                pointer = pointer.left
            } else if (pointer.data > data) {
                preNode = pointer
                pointer = pointer.right
            } else {
                if (preNode?.left?.data == data) {
                    preNode.left = preNode.left!!.nextWhenRemove()
                } else {
                    preNode!!.right = preNode.right!!.nextWhenRemove()
                }
                return
            }
        }
    }

    fun printAll() {
        inorder()
    }

    private fun inorder() {
        if (root == null) {
            return
        } else {
            inorder(root!!)
        }
    }

    private fun inorder(node: Node) {
        if (node.left != null) {
            inorder(node.left!!)
        }
        println(node.data)
        if (node.right != null) {
            inorder(node.right!!)
        }
    }
}

class Node(var data: String) {
    var left: Node? = null
    var right: Node? = null
    fun nextWhenRemove():Node?{
        return if(left == null && right == null){
            null
        }else if(left != null && right != null){
            right = left!!.right
            left
        }else if(left != null){
            left
        }else{
            right
        }
    }
    fun hasChild(): Boolean {
        return !(left == null && right == null)
    }
}