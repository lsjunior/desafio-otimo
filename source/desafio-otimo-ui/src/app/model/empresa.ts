export interface Empresa {
  id?: number;
  cnpj?: string;
  nome?: string;
  razaoSocial?: string;
  contato?: string;
  email?: string;
  cep?: string;
  estado?: string;
  bairro?: string;
  cidade?: string;
  logradouro?: string;
  complemento?: string;
  tipo?: string;
  matriz?: Empresa;
}
