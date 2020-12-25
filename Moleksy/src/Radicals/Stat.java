package Radicals;

/**
 * probably stat of radical
 * atom/radical/binding
 */

public enum  Stat {
    Atom,
    Radical,
    Binding;

    /**
     * parse string in stat
     * @param name string-stat
     * @return stat object or null if cant parse
     */
    public static Stat parseStat(String name){
        if(name.equals(Atom.name())){
            return Atom;
        }
        if(name.equals(Radical.name())){
            return Radical;
        }
        if(name.equals(Binding.name())){
            return Binding;
        }
        return null;
    }
}
