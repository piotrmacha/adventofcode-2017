import java.io.File

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Provide file path as first argument");
    }

    var registry = HashMap<String, Long>()
    registry["IP"] = 0
    registry["SOUND"] = 0
    for (c in 'a'..'z') {
        registry[c.toString()] = 0;
    }

    val program = File(args[0]).readLines()
    registry["PROGRAM_SIZE"] = program.size.toLong()

    while (registry["IP"]!! < registry["PROGRAM_SIZE"]!!) {
       registry = cycle(program[registry["IP"]!!.toInt()], registry)
    }

    registry.forEach { key, value -> println("%s = %s".format(key, value)) }
}

fun cycle(line: String, previousRegistry: HashMap<String, Long>): HashMap<String, Long> {
    val registry = HashMap(previousRegistry)
    val parts = line.split(" ")
    val instruction = parts[0]
    val lop = parts[1]
    val rop: String? = parts.getOrNull(2)

    when (instruction) {
        "set" -> {
            registry[lop] = when {
                isRegister(rop!!) -> registry[rop]
                else -> rop.toLong()
            }
            registry["IP"] = registry["IP"]!! + 1
        }
        "add" -> {
            registry[lop] = when {
                isRegister(rop!!) -> registry[lop]!! + registry[rop]!!
                else -> registry[lop]!! + rop.toLong()
            }
            registry["IP"] = registry["IP"]!! + 1
        }
        "mul" -> {
            registry[lop] = when {
                isRegister(rop!!) -> registry[lop]!! * registry[rop]!!
                else -> registry[lop]!! * rop.toLong()
            }
            registry["IP"] = registry["IP"]!! + 1
        }
        "mod" -> {
            registry[lop] = when {
                isRegister(rop!!) -> Math.floorMod(registry[lop]!!, registry[rop]!!)
                else -> Math.floorMod(registry[lop]!!, rop.toLong())
            }
            registry["IP"] = registry["IP"]!! + 1
        }
        "snd" -> {
            registry["SOUND"] = when {
                isRegister(lop) -> registry[lop]!!
                else -> lop.toLong()
            }
            registry["IP"] = registry["IP"]!! + 1
        }
        "jgz" -> {
            fun isConditionMet(lop: String): Boolean {
                return (isRegister(lop) && registry[lop]!! > 0) || (!isRegister(lop) && lop.toLong() > 0)
            }
            registry["IP"] = when {
                isConditionMet(lop) && !isRegister(rop!!) -> registry["IP"]!! + rop.toLong()
                isConditionMet(lop) && isRegister(rop!!) -> registry["IP"]!! + registry[rop]!!
                else -> registry["IP"]!! + 1
            }
        }
        "rcv" -> {
            val recover = when {
                isRegister(lop) -> registry[lop]!! != 0L
                else -> lop.toLong() != 0L
            }
            registry["IP"] = when (recover) {
                true -> registry["PROGRAM_SIZE"]
                false -> registry["IP"]!! + 1
            }
        }
    }

    return registry
}

fun isRegister(input: String): Boolean {
    return input[0] in 'a'..'z'
}