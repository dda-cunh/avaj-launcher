.SILENT:
NAME		=	exec.jar

JAVAC		=	javac

JFLAGS		= 	-Xlint

RM 			= 	rm -rf

OUT_DIR		=	temp/

SRC_DIR		=	src/

INC_DIR		=	inc/

MANIFEST_F	=	MANIFEST.MF

MAIN_CLASS	=	src.Main

SRC			=	$(wildcard $(addprefix $(SRC_DIR),	*.java)) \
				$(wildcard $(addprefix $(INC_DIR),	*.java))

GREEN		= 	\033[0;32m

RESET		=	\033[0m

SUS			=	\U00000D9E

HAMMER		=	\U0001F528

BROOM		=	\U0001F9F9

CHECK_MARK	=	\U00002705

$(NAME):		$(OUT_DIR)
				printf '$(HAMMER)\n\t$(GREEN)Compiling	$(NAME)$(RESET)\n'
				make manifest
				jar cvmf $(MANIFEST_F) $(NAME) -C $(OUT_DIR) .
				chmod +x $(NAME)
				make compiled

all: 			$(NAME)

$(OUT_DIR):		$(SRC)
				$(JAVAC) $(JFLAGS) $(SRC) -d $(OUT_DIR)

manifest:
				echo 'Manifest-Version: 1.0.0' > $(MANIFEST_F)
				echo 'Created-By: dda-cunh' >> $(MANIFEST_F)
				echo 'Main-Class: $(MAIN_CLASS)' >> $(MANIFEST_F)

run:			$(NAME)
				java -jar $(NAME) $(ARGS)

list:			$(NAME)
				jar tf $(NAME)

clean:
				printf '$(BROOM)\n$(BROOM)\t$(GREEN)Cleaning objects$(RESET)\n'
				$(RM) $(OUT_DIR)

fclean:			clean
				printf '$(BROOM)\n$(BROOM)\t$(GREEN)Cleaning project$(RESET)\n'
				$(RM) $(NAME)
				$(RM) $(MANIFEST_F)
				printf '$(BROOM)\t\t\t$(SUS)\n'

re:				fclean	all

compiled:
				printf "															 	\n"
				printf "$(GREEN)	$(NAME)							 			$(RESET)\n"
				printf "$(GREEN)                             _  _             _ $(RESET)\n"
				printf "$(GREEN)                            (_)| |           | |$(RESET)\n"
				printf "$(GREEN)  ____   ___   ____   ____   _ | |  ____   _ | |$(RESET)\n"
				printf "$(GREEN) / ___) / _ \ |    \ |  _ \ | || | / _  ) / || |$(RESET)\n"
				printf "$(GREEN)( (___ | |_| || | | || | | || || |( (/ / ( (_| |$(RESET)\n"
				printf "$(GREEN) \____) \___/ |_|_|_|| ||_/ |_||_| \____) \____|$(RESET)\n"
				printf "$(GREEN)                     |_|                        $(RESET)\n"
				printf "																\n"

.PHONY: 		all run manifest fclean re done compiled compile
