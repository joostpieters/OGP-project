package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.Worm;

public class GetMaxAP extends Expression {

		public GetMaxAP(Expression entity, int line, int column) {
			super(line, column);
			this.entity = (EntityExpression) entity;
		}

		@Override
		public EntityExpression getValue(Program program) {
			return entity;
		}

		public Integer getResult() {
			if (!(entity.getResult() instanceof Worm))
				return null;
			return ((Worm) entity.getResult()).getMaxActionPoints();
		}
		
		@Override
		public Integer getResult(Program program) {
			return getResult();
		}

		private final EntityExpression entity;
	}
