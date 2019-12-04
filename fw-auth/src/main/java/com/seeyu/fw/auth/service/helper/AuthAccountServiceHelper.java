package com.seeyu.fw.auth.service.helper;

import com.seeyu.core.utils.Alert;
import com.seeyu.core.utils.Assert;
import com.seeyu.fw.auth.constant.message.AccountMessageConstant;
import com.seeyu.fw.auth.constant.message.SystemMessageConstant;
import com.seeyu.fw.auth.constant.option.GlobalOptions;
import com.seeyu.fw.auth.entity.AuthAccount;
import com.seeyu.fw.auth.entity.AuthSystemAccount;
import com.seeyu.fw.auth.exception.AccountAlreadyExistException;
import com.seeyu.fw.auth.exception.AccountNotExistException;
import com.seeyu.fw.auth.mapper.AuthAccountMapper;
import com.seeyu.fw.auth.service.AuthSystemAccountService;
import com.seeyu.fw.auth.vo.AuthAccountAddModel;
import com.seeyu.normal.utils.FormValidator;
import com.seeyu.normal.utils.FormValidator.ValidateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author seeyu
 * @date 2019/4/26
 */
@Service
public class AuthAccountServiceHelper {

    public static final int ACCOUNT_LENGTH_MAX = 50;
    public static final int ACCOUNT_LENGTH_MIN = 2;

    public static final int ACCOUNT_PASSWORD_LENGTH_MAX = 20;
    public static final int ACCOUNT_PASSWORD_LENGTH_MIN = 6;

//    @Autowired
//    private JwtService jwtService;
    @Autowired
    private AuthAccountMapper accountMapper;
//    @Autowired
//    private AdminAccountMapper adminAccountMapper;
//    @Autowired
//    private RoleMapper roleMapper;
//    @Autowired
//    private MenuMapper menuMapper;
//    @Autowired
//    private ResourceMapper resourceMapper;
    @Autowired
    private AuthSystemAccountService systemAccountService;


    @Transactional(rollbackFor = Exception.class)
    public void updateAccountPassword(Integer accountId, String password, boolean reset) throws AccountNotExistException {
        AuthAccount normalAccount = this.accountMapper.selectByPrimaryKey(accountId);
        if(normalAccount != null){
            if(reset){
                this.resetAccountPassword(normalAccount.getAccountId(), password);
            }
            else{
                this.modifyAccountPassword(normalAccount.getAccountId(), password);
            }
        }
        else{
//            AuthSystemAccount systemAccount = this.systemAccountService.getSystemAccountById(accountId);
//            if(systemAccount != null){
//
//            }
//            else{
                throw new AccountNotExistException();
//            }
        }
    }

    private void modifyAccountPassword(Integer accountId, String password){
        this.accountMapper.updateAccountPassword(accountId, password, new Date());
    }

    private void resetAccountPassword(Integer accountId, String password){
        this.accountMapper.updateAccountPassword(accountId, password, null);
    }

//    public UserInfo authentication(AuthAccount account) throws Exception {
//        FormValidator.required(AccountMessageConstant.TITLE_ACCOUNT_ACCOUNT, account.getActAccount());
//        FormValidator.required(AccountMessageConstant.TITLE_ACCOUNT_PASSWORD, account.getActPassword());
//
//        Account theAccount = accountMapper.getLoginAccount(account.getActAccount());
//        boolean isAdmin = false;
//        if(theAccount  == null){
//            isAdmin = true;
//            theAccount = adminAccountMapper.getLoginAccount(account.getActAccount());
//        }
//        //检查密码匹配
//        Assert.notNull(theAccount, AccountMessageConstant.ACCOUNT_OR_PASSWORD_NOT_MATCH);
//        Alert.alert(BaseController.passwordValidate(theAccount.getActPassword(), account.getActPassword()), AccountMessageConstant.ACCOUNT_OR_PASSWORD_NOT_MATCH);
//        UserInfo userInfo = wrapLoginUserResourceData(theAccount, isAdmin);
//        userInfo.setToken(createToken(userInfo,  account.getInfo()));
//        return userInfo;
//    }

//    public void modifyPassword(AuthModifyPassWordModel modifyPassWord) throws Exception {
//        FormValidator.required(AccountMessageConstant.TITLE_ACCOUNT_PASSWORD, modifyPassWord.getPassword());
//        FormValidator.required(AccountMessageConstant.TITLE_ACCOUNT_NEW_PASSWORD, modifyPassWord.getNewPassword());
//        FormValidator.required(AccountMessageConstant.TITLE_ACCOUNT_CONFIRM_PASSWORD, modifyPassWord.getConfirmPassword());
//        Assert.equals(modifyPassWord.getNewPassword(), modifyPassWord.getConfirmPassword(), AccountMessageConstant.CONFIRM_PASSWORD_NOT_EQUAL);
//
//        LoginUserInfo loginUserInfo = LoginUserInfoContext.getLoginUser();
//
//        if(loginUserInfo.isAdmin()){
//            this.modifyAdminAccountPassword(loginUserInfo.getActId(), modifyPassWord);
//        }
//        else{
//            this.modifyAccountPassword(loginUserInfo.getActId(), modifyPassWord);
//        }
//    }


//    public Account wrapEditAccount(AccountTo editAccount) {
//        Account account = accountMapper.selectByPrimaryKey(editAccount.getActId());
//        Alert.alert(account != null, SystemMessageConstant.OBJECT_NOT_EXIST, AccountMessageConstant.TITLE_ACCOUNT);
//        account.setActName(editAccount.getActName());
//        account.setActPhone(editAccount.getActPhone());
//        account.setActEmail(editAccount.getActEmail());
//        account.setActGender(editAccount.getActGender());
//        return account;
//    }


    public AuthAccount wrapAddAccount(AuthAccountAddModel model) {
        AuthAccount account = new AuthAccount();
        account.setAccountAccount(model.getAccountAccount());
        account.setAccountPassword(model.getAccountPassword());
        account.setAccountActive(model.getAccountActive());
        return account;
    }



    public void validateAccount(AuthAccount account) throws AccountAlreadyExistException {
        //账户验证
        FormValidator.validate(AccountMessageConstant.TITLE_ACCOUNT_ACCOUNT, account.getAccountAccount(), FormValidator.ValidateType.required, ValidateType.noBlank);
        FormValidator.rangeLength(AccountMessageConstant.TITLE_ACCOUNT_ACCOUNT, account.getAccountAccount(), ACCOUNT_LENGTH_MIN, ACCOUNT_LENGTH_MAX);
        //密码验证
        FormValidator.validate(AccountMessageConstant.TITLE_ACCOUNT_PASSWORD, account.getAccountPassword(), ValidateType.required);
        //激活状态验证
        FormValidator.required(AccountMessageConstant.TITLE_ACCOUNT_ACTIVE, account.getAccountActive());
        FormValidator.inOptions(AccountMessageConstant.TITLE_ACCOUNT_ACTIVE, GlobalOptions.activationOptions, account.getAccountActive());

        //账户存在
        if(account.getAccountId() == null){
            boolean accountExist = this.normalOrSystemAccountExist(account.getAccountAccount());
            if(accountExist){
                throw new AccountAlreadyExistException();
            }
        }
    }


//    public RegisterAccount cast2RegisterAccount(AccountRolesTo accountRoles){
//        RegisterAccount ra = new RegisterAccount();
//        ra.setActAccount(accountRoles.getActAccount());
//        ra.setActEmail(accountRoles.getActEmail());
//        ra.setActGender(accountRoles.getActGender());
//        ra.setActName(accountRoles.getActName());
//        ra.setActPhone(accountRoles.getActPhone());
//        return ra;
//    }


//    private String createToken(UserInfo user, Map<String, Object> info) throws Exception {
//        return jwtService.createToken(BeanUtils.transBean2Map(warpLoginUserInfo(user, info), new HashMap<>(10)));
//    }


//    private LoginUserInfo warpLoginUserInfo(UserInfo user, Map<String, Object> info){
//        LoginUserInfo loginUserInfo = new LoginUserInfo();
//        loginUserInfo.setActAccount(user.getActAccount());
//        loginUserInfo.setActActive(user.getActActive());
//        loginUserInfo.setActAddTime(user.getActAddTime());
//        loginUserInfo.setActEmail(user.getActEmail());
//        loginUserInfo.setActGender(user.getActGender());
//        loginUserInfo.setActId(user.getActId());
//        loginUserInfo.setActName(user.getActName());
//        loginUserInfo.setActPhone(user.getActPhone());
//        loginUserInfo.setAdmin(user.isAdmin());
//        loginUserInfo.setInfo(info);
//        loginUserInfo.setRoleIds(this.getRoleIds(user));
//        return loginUserInfo;
//    }


//    private List<Role> getUserRoles(Integer actId){
//        return roleMapper.getUserRoleList(actId);
//    }


//    private List<MenuPlain> getUserPlainMenus(Integer actId){
//        List<MenuResource> allMenus = getServiceActiveMenus(getServiceId());
//        List<Resource> userResources = resourceMapper.getUserActiveResources(actId, getServiceId());
//        return new MenuTreeHelper(allMenus, userResources).getUserPlainMenus();
//    }


//    private List<MenuPlain> getAdminPlainMenus(){
//        List<MenuResource> allMenus = getServiceActiveMenus(getServiceId());
//        List<Resource> adminResources = resourceMapper.getAdminActiveResources(getServiceId());
//        return new MenuTreeHelper(allMenus, adminResources).getUserPlainMenus();
//    }


//    private List<Resource> getUserActiveResources(Integer actId){
//        return resourceMapper.getUserActiveResources(actId, getServiceId());
//    }


//    private UserInfo wrapLoginUserResourceData(Account account, boolean isAdmin){
//        UserInfo userInfo = UserInfo.wrap(account);
//        if(isAdmin){
//            userInfo.setAdmin(true);
//            userInfo.setMenus(getAdminPlainMenus());
//        }
//        else{
//            userInfo.setRoles(getUserRoles(account.getActId()));
//            //获取菜单
//            userInfo.setMenus(getUserPlainMenus(account.getActId()));
//            //获取用户资源
//            //userInfo.setResources(getUserActiveResources(account.getActId()));
//        }
//        return userInfo;
//    }


    private boolean normalOrSystemAccountExist(String account){
        return this.accountMapper.getNormalOrSystemAccountCount(account) != 0;
    }



//    private boolean emailExist(Integer actId, String actEmail){
//        if(StringUtils.isBlank(actEmail)){
//            return false;
//        }
//        return accountMapper.getEmailCount(actId, actEmail) != 0;
//    }


//    private boolean phoneExist(Integer actId, String actPhone){
//        if(StringUtils.isBlank(actPhone)){
//            return false;
//        }
//        return accountMapper.getPhoneCount(actId, actPhone) != 0;
//    }


//    private List<MenuResource> getServiceActiveMenus(Integer serviceId){
//        MenuResource menu = new MenuResource();
//        menu.setReeActive(ActivationState.getActiveState());
//        menu.setMeuSeeid(serviceId);
//        return menuMapper.getMenuResources(menu);
//    }

//    private Integer getServiceId (){
//        ServiceInfo serviceInfo = ServiceInfoContext.get();
//        return serviceInfo == null ? -1 : serviceInfo.getSeeId();
//    }

//    private List<Integer> getRoleIds(UserInfo user){
//        if(user.getRoles() == null){
//            return null;
//        }
//        Set<Integer> roleIds = new HashSet<>(user.getRoles().size());
//        for(Role role : user.getRoles()){
//            roleIds.add(role.getRoeId());
//        }
//        return new ArrayList<>(roleIds);
//    }

//    private void modifyAccountPassword(Integer actId, ModifyPassWord modifyPassWord) throws Exception {
//        Account account = accountMapper.selectByPrimaryKey(actId);
//        validatePasswordMatch(account.getActPassword(), modifyPassWord.getPassword());
//        account.setActPassword(BaseController.passwordEncrypt(modifyPassWord.getNewPassword()));
//        accountMapper.updatePassword(account);
//    }

//    private void modifyAdminAccountPassword(Integer actId, ModifyPassWord modifyPassWord) throws Exception {
//        AdminAccount adminAccount = adminAccountMapper.selectByPrimaryKey(actId);
//        validatePasswordMatch(adminAccount.getActPassword(), modifyPassWord.getPassword());
//        adminAccount.setActPassword(BaseController.passwordEncrypt(modifyPassWord.getNewPassword()));
//        adminAccountMapper.updatePassword(adminAccount);
//    }

//    private void validatePasswordMatch(String accountPassword, String matchPassword) throws Exception {
//        Alert.alert(BaseController.passwordValidate(accountPassword, matchPassword), AccountMessageConstant.PASSWORD_NOT_MATCH);
//    }

}
