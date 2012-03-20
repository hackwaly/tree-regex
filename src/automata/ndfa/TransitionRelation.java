package automata.ndfa;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import automata.composition.Alphabet;
import automata.core.State;
import automata.core.Symbol;
import automata.core.Transition;


public class TransitionRelation implements Iterable<Transition>
{

	private Set<Transition>	transitions;


	public TransitionRelation()
	{
		this.transitions = new LinkedHashSet<Transition>();
	}


	public Set<State> canReach(State start, Symbol symbol)
	{
		Set<State> result = new LinkedHashSet<>();

		for (Transition transition : transitions)
		{
			if (transition.getStart() == start && transition.getSymbol() == symbol)
			{
				result.add(transition.getEnd());
				result.addAll(canReach(transition.getEnd(), Alphabet.EMPTY));
			}
		}

		return result;
	}


	public void add(Transition transition)
	{
		this.transitions.add(transition);
	}


	public void remove(Transition transition)
	{
		this.transitions.remove(transition);
	}


	public boolean contains(Transition transition)
	{
		return this.transitions.contains(transition);
	}


	@Override
	public String toString()
	{
		String result = "\u0394: \t| ";
		for (Transition transition : this)
		{
			result += transition + "\n\t| ";
		}
		return result.substring(0, result.length() - 4);
	}


	@Override
	public Iterator<Transition> iterator()
	{
		return this.transitions.iterator();
	}
}
